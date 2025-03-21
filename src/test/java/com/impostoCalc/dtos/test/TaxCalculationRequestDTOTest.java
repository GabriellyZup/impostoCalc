package com.impostoCalc.dtos.test;

import com.impostoCalc.dtos.TaxCalculationRequestDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaxCalculationRequestDTOTest {

    @Test
    void testTaxCalculationRequestDTO() {
        // Arrange
        TaxCalculationRequestDTO requestDTO = new TaxCalculationRequestDTO();
        requestDTO.setTipoImpostoId(1);
        requestDTO.setValorBase(BigDecimal.valueOf(1000.0));

        // Assert
        assertEquals(1, requestDTO.getTipoImpostoId());
        assertEquals(BigDecimal.valueOf(1000.0), requestDTO.getValorBase());
    }
}