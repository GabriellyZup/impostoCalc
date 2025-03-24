package com.impostoCalc.dtos.test;

import com.impostoCalc.dtos.request.TaxTypeRequestDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TaxTypeRequestDTOTest {

    @Test
    void testTaxTypeRequestDTO() {
        // Arrange
        TaxTypeRequestDTO requestDTO = new TaxTypeRequestDTO();
        String nome = "ICMS";
        String descricao = "Imposto sobre Circulação de Mercadorias e Serviços";
        BigDecimal aliquota = BigDecimal.valueOf(18.00);

        // Act
        requestDTO.setNome(nome);
        requestDTO.setDescricao(descricao);
        requestDTO.setAliquota(aliquota);

        // Assert
        assertEquals(nome, requestDTO.getNome());
        assertEquals(descricao, requestDTO.getDescricao());
        assertEquals(aliquota, requestDTO.getAliquota());
    }
}