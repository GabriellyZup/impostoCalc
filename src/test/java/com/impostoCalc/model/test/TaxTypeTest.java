package com.impostoCalc.model.test;

import com.impostoCalc.model.TaxType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TaxTypeTest {

    @Test
    void testTaxTypeGettersAndSetters() {
        // Arrange
        TaxType taxType = new TaxType();
        Integer id = 1;
        String nome = "ICMS";
        String descricao = "Imposto sobre Circulação de Mercadorias e Serviços";
        BigDecimal aliquota = BigDecimal.valueOf(18.00);

        // Act
        taxType.setId(id);
        taxType.setNome(nome);
        taxType.setDescricao(descricao);
        taxType.setAliquota(aliquota);

        // Assert
        assertEquals(id, taxType.getId());
        assertEquals(nome, taxType.getNome());
        assertEquals(descricao, taxType.getDescricao());
        assertEquals(aliquota, taxType.getAliquota());
    }

    @Test
    void testTaxTypeDefaultValues() {
        // Arrange
        TaxType taxType = new TaxType();

        // Assert
        assertNull(taxType.getId());
        assertNull(taxType.getNome());
        assertNull(taxType.getDescricao());
        assertNull(taxType.getAliquota());
    }
}