package com.impostoCalc.dtos.test;

import com.impostoCalc.dtos.TaxTypeRequestDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaxTypeRequestDTOTest {

    @Test
    void testTaxTypeRequestDTO() {
        // Arrange
        TaxTypeRequestDTO requestDTO = new TaxTypeRequestDTO();
        requestDTO.setNome("ICMS");
        requestDTO.setDescricao("Imposto sobre Circulação de Mercadorias e Serviços");
        requestDTO.setAliquota(BigDecimal.valueOf(18.0));

        // Assert
        assertEquals("ICMS", requestDTO.getNome());
        assertEquals("Imposto sobre Circulação de Mercadorias e Serviços", requestDTO.getDescricao());
        assertEquals(BigDecimal.valueOf(18.0), requestDTO.getAliquota());
    }
}