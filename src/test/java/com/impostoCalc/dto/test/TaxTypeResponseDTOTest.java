package com.impostoCalc.dto.test;

import com.impostoCalc.dtos.TaxTypeResponseDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaxTypeResponseDTOTest {

    @Test
    public void testSettersAndGetters() {
        TaxTypeResponseDTO dto = new TaxTypeResponseDTO();
        dto.setId(1);
        dto.setNome("ICMS");
        dto.setDescricao("Imposto sobre Circulação de Mercadorias e Serviços");
        dto.setAliquota(new BigDecimal("18.00"));

        assertEquals(1, dto.getId());
        assertEquals("ICMS", dto.getNome());
        assertEquals("Imposto sobre Circulação de Mercadorias e Serviços", dto.getDescricao());
        assertEquals(new BigDecimal("18.00"), dto.getAliquota());
    }
}