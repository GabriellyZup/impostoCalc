package com.impostoCalc.dto.test;

import com.impostoCalc.dtos.TaxCalculationResponseDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaxCalculationResponseDTOTest {

    @Test
    public void testSettersAndGetters() {
        TaxCalculationResponseDTO dto = new TaxCalculationResponseDTO();
        dto.setTipoImposto("ICMS");
        dto.setValorBase(new BigDecimal("1000.00"));
        dto.setAliquota(new BigDecimal("18.00"));
        dto.setValorImposto(new BigDecimal("180.00"));

        assertEquals("ICMS", dto.getTipoImposto());
        assertEquals(new BigDecimal("1000.00"), dto.getValorBase());
        assertEquals(new BigDecimal("18.00"), dto.getAliquota());
        assertEquals(new BigDecimal("180.00"), dto.getValorImposto());
    }
}