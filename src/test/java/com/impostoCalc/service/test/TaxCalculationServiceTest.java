package com.impostoCalc.service.test;

import com.impostoCalc.dtos.request.TaxCalculationRequestDTO;
import com.impostoCalc.dtos.response.TaxCalculationResponseDTO;
import com.impostoCalc.model.TaxType;
import com.impostoCalc.repository.TaxTypeRepository;
import com.impostoCalc.service.TaxCalculationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaxCalculationServiceTest {

    @Mock
    private TaxTypeRepository taxTypeRepository;

    @InjectMocks
    private TaxCalculationService taxCalculationService;

    @Test
    void testCalculateTax() {
        TaxCalculationRequestDTO request = new TaxCalculationRequestDTO();
        request.setTipoImpostoId(1);
        request.setValorBase(BigDecimal.valueOf(1000));

        TaxType taxType = new TaxType(1, "ICMS", "Descrição", BigDecimal.valueOf(18));
        when(taxTypeRepository.findById(1)).thenReturn(Optional.of(taxType));
        // Act
        TaxCalculationResponseDTO response = taxCalculationService.calculateTax(request);

        // Assert
        assertNotNull(response);
        assertEquals("ICMS", response.getTipoImposto());
        assertEquals(0, BigDecimal.valueOf(180.0).compareTo(response.getValorImposto()));
    }
}