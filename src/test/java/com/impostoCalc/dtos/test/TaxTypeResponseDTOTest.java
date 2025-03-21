package com.impostoCalc.dtos.test;

import com.impostoCalc.dtos.TaxTypeResponseDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaxTypeResponseDTOTest {

    @Test
    void testTaxTypeResponseDTO() {
        // Arrange
        TaxTypeResponseDTO responseDTO = new TaxTypeResponseDTO();
        responseDTO.setId(1);
        responseDTO.setNome("ICMS");
        responseDTO.setDescricao("Imposto sobre Circulação de Mercadorias e Serviços");
        responseDTO.setAliquota(BigDecimal.valueOf(18.0));

        // Assert
        assertEquals(1, responseDTO.getId());
        assertEquals("ICMS", responseDTO.getNome());
        assertEquals("Imposto sobre Circulação de Mercadorias e Serviços", responseDTO.getDescricao());
        assertEquals(BigDecimal.valueOf(18.0), responseDTO.getAliquota());
    }
}