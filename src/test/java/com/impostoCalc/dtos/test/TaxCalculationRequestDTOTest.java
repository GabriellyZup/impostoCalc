package com.impostoCalc.dtos.test;

import com.impostoCalc.dtos.request.TaxCalculationRequestDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TaxCalculationRequestDTOTest {

    @Test
    void testTaxCalculationRequestDTO() {
        // Arrange
        TaxCalculationRequestDTO requestDTO = new TaxCalculationRequestDTO();
        Integer tipoImpostoId = 1;
        BigDecimal valorBase = BigDecimal.valueOf(100.00);

        // Act
        requestDTO.setTipoImpostoId(tipoImpostoId);
        requestDTO.setValorBase(valorBase);

        // Assert
        assertEquals(tipoImpostoId, requestDTO.getTipoImpostoId());
        assertEquals(valorBase, requestDTO.getValorBase());
    }
}