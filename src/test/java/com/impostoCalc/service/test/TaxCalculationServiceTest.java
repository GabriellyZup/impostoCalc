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
        assertEquals(BigDecimal.valueOf(180), response.getValorImposto());
        verify(taxTypeRepository, times(1)).findById(1);
    }

    @Test
    void testCalculateTax_TaxTypeNotFound() {
        // Arrange
        TaxCalculationRequestDTO request = new TaxCalculationRequestDTO();
        request.setTipoImpostoId(1);
        request.setValorBase(BigDecimal.valueOf(1000));

        when(taxTypeRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            taxCalculationService.calculateTax(request);
        });

        assertEquals("Tipo de imposto não encontrado para o ID fornecido.", exception.getMessage());
        verify(taxTypeRepository, times(1)).findById(1);
    }
}