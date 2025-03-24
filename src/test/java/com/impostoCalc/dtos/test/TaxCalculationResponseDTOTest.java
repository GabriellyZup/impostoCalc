package com.impostoCalc.dtos.test;

import com.impostoCalc.dtos.response.TaxCalculationResponseDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TaxCalculationResponseDTOTest {

    @Test
    void testTaxCalculationResponseDTO() {
        // Arrange
        String tipoImposto = "ICMS";
        BigDecimal valorBase = BigDecimal.valueOf(1000.00);
        BigDecimal aliquota = BigDecimal.valueOf(18.00);
        BigDecimal valorImposto = BigDecimal.valueOf(180.00);

        // Act
        TaxCalculationResponseDTO responseDTO = new TaxCalculationResponseDTO(tipoImposto, valorBase, aliquota, valorImposto);

        // Assert
        assertEquals(tipoImposto, responseDTO.getTipoImposto());
        assertEquals(valorBase, responseDTO.getValorBase());
        assertEquals(aliquota, responseDTO.getAliquota());
        assertEquals(valorImposto, responseDTO.getValorImposto());
    }
}