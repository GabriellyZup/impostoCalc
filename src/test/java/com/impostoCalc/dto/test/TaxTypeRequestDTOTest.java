package com.impostoCalc.dto.test;

import com.impostoCalc.dtos.TaxTypeRequestDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaxTypeRequestDTOTest {

    private final Validator validator;

    public TaxTypeRequestDTOTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Test
    public void testValidDTO() {
        TaxTypeRequestDTO dto = new TaxTypeRequestDTO();
        dto.setNome("ICMS");
        dto.setDescricao("Imposto sobre Circulação de Mercadorias e Serviços");
        dto.setAliquota(new BigDecimal("18.00"));

        Set<ConstraintViolation<TaxTypeRequestDTO>> violations = validator.validate(dto);
        assertEquals(0, violations.size(), "DTO válido não deve ter violações");
    }

    @Test
    public void testInvalidDTO() {
        TaxTypeRequestDTO dto = new TaxTypeRequestDTO();
        dto.setNome(null); // Campo obrigatório
        dto.setAliquota(null); // Campo obrigatório

        Set<ConstraintViolation<TaxTypeRequestDTO>> violations = validator.validate(dto);
        assertEquals(3, violations.size(), "DTO inválido deve ter 3 violações");
    }
}