package com.impostoCalc.dtos.test;

import com.impostoCalc.dtos.response.TaxTypeResponseDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TaxTypeResponseDTOTest {

    @Test
    void testTaxTypeResponseDTO() {
        // Arrange
        Integer id = 1;
        String nome = "ICMS";
        String descricao = "Imposto sobre Circulação de Mercadorias e Serviços";
        BigDecimal aliquota = BigDecimal.valueOf(18.00);

        // Act
        TaxTypeResponseDTO responseDTO = new TaxTypeResponseDTO(id, nome, descricao, aliquota);

        // Assert
        assertEquals(id, responseDTO.getId());
        assertEquals(nome, responseDTO.getNome());
        assertEquals(descricao, responseDTO.getDescricao());
        assertEquals(aliquota, responseDTO.getAliquota());
    }
}