package com.impostoCalc.model.test;

import com.impostoCalc.model.TaxType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TaxTypeTest {

    @Test
    void testTaxType() {
        // Arrange
        Integer id = 1;
        String nome = "ICMS";
        String descricao = "Imposto sobre Circulação de Mercadorias e Serviços";
        BigDecimal aliquota = BigDecimal.valueOf(18.00);

        // Act
        TaxType taxType = new TaxType(id, nome, descricao, aliquota);

        // Assert
        assertEquals(id, taxType.getId());
        assertEquals(nome, taxType.getNome());
        assertEquals(descricao, taxType.getDescricao());
        assertEquals(aliquota, taxType.getAliquota());
    }
}