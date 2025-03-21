package com.impostoCalc.model.test;

import com.impostoCalc.model.TaxType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TaxTypeTest {

    @Test
    void testTaxTypeModel() {
        // Arrange
        TaxType taxType = new TaxType();
        taxType.setId(1);
        taxType.setNome("ICMS");
        taxType.setDescricao("Imposto sobre Circulação de Mercadorias e Serviços");
        taxType.setAliquota(BigDecimal.valueOf(18.0));

        // Assert
        assertEquals(1, taxType.getId());
        assertEquals("ICMS", taxType.getNome());
        assertEquals("Imposto sobre Circulação de Mercadorias e Serviços", taxType.getDescricao());
        assertEquals(BigDecimal.valueOf(18.0), taxType.getAliquota());
    }

    @Test
    void testTaxTypeConstructor() {
        // Arrange
        TaxType taxType = new TaxType(1, "ICMS", "Imposto sobre Circulação de Mercadorias e Serviços", BigDecimal.valueOf(18.0));

        // Assert
        assertNotNull(taxType);
        assertEquals(1, taxType.getId());
        assertEquals("ICMS", taxType.getNome());
        assertEquals("Imposto sobre Circulação de Mercadorias e Serviços", taxType.getDescricao());
        assertEquals(BigDecimal.valueOf(18.0), taxType.getAliquota());
    }
}