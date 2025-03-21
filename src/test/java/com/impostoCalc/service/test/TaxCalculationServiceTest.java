package com.impostoCalc.service.test;

import com.impostoCalc.dtos.TaxCalculationRequestDTO;
import com.impostoCalc.dtos.TaxCalculationResponseDTO;
import com.impostoCalc.model.TaxType;
import com.impostoCalc.repository.TaxTypeRepository;
import com.impostoCalc.service.TaxCalculationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaxCalculationServiceTest {

    @InjectMocks
    private TaxCalculationService taxCalculationService;

    @Mock
    private TaxTypeRepository taxTypeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculateTax_Success() {
        // Arrange
        TaxCalculationRequestDTO request = new TaxCalculationRequestDTO();
        request.setTipoImpostoId(1);
        request.setValorBase(BigDecimal.valueOf(1000));

        TaxType taxType = new TaxType();
        taxType.setId(1);
        taxType.setNome("ICMS");
        taxType.setAliquota(BigDecimal.valueOf(18));

        when(taxTypeRepository.findById(1)).thenReturn(Optional.of(taxType));

        // Act
        TaxCalculationResponseDTO response = taxCalculationService.calculateTax(request);

        // Assert
        assertNotNull(response);
        assertEquals("ICMS", response.getTipoImposto());
        assertEquals(BigDecimal.valueOf(1000), response.getValorBase());
        assertEquals(BigDecimal.valueOf(18), response.getAliquota());
        assertEquals(BigDecimal.valueOf(180.0), response.getValorImposto());
        verify(taxTypeRepository, times(1)).findById(1);
    }

    @Test
    void testCalculateTax_TaxTypeNotFound() {
        // Arrange
        TaxCalculationRequestDTO request = new TaxCalculationRequestDTO();
        request.setTipoImpostoId(1);

        when(taxTypeRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> taxCalculationService.calculateTax(request));
        assertEquals("404 NOT_FOUND \"Tipo de imposto não encontrado\"", exception.getMessage());
        verify(taxTypeRepository, times(1)).findById(1);
    }

    @Test
    void testCalculateTax_InvalidAliquota() {
        // Arrange
        TaxCalculationRequestDTO request = new TaxCalculationRequestDTO();
        request.setTipoImpostoId(1);

        TaxType taxType = new TaxType();
        taxType.setId(1);
        taxType.setAliquota(BigDecimal.ZERO);

        when(taxTypeRepository.findById(1)).thenReturn(Optional.of(taxType));

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> taxCalculationService.calculateTax(request));
        assertEquals("400 BAD_REQUEST \"Alíquota inválida\"", exception.getMessage());
        verify(taxTypeRepository, times(1)).findById(1);
    }
}