package com.impostoCalc.dtos.test;

import com.impostoCalc.dtos.TaxCalculationResponseDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaxCalculationResponseDTOTest {

    @Test
    void testTaxCalculationResponseDTO() {
        // Arrange
        TaxCalculationResponseDTO responseDTO = new TaxCalculationResponseDTO();
        responseDTO.setTipoImposto("ICMS");
        responseDTO.setValorBase(BigDecimal.valueOf(1000.0));
        responseDTO.setAliquota(BigDecimal.valueOf(18.0));
        responseDTO.setValorImposto(BigDecimal.valueOf(180.0));

        // Assert
        assertEquals("ICMS", responseDTO.getTipoImposto());
        assertEquals(BigDecimal.valueOf(1000.0), responseDTO.getValorBase());
        assertEquals(BigDecimal.valueOf(18.0), responseDTO.getAliquota());
        assertEquals(BigDecimal.valueOf(180.0), responseDTO.getValorImposto());
    }
}