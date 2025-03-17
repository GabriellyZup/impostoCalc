package com.impostoCalc.dto.test;

import com.impostoCalc.dtos.TaxCalculationRequestDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaxCalculationRequestDTOTest {

    private final Validator validator;

    public TaxCalculationRequestDTOTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Test
    public void testValidDTO() {
        TaxCalculationRequestDTO dto = new TaxCalculationRequestDTO();
        dto.setTipoImpostoId(1);
        dto.setValorBase(new BigDecimal("1000.00"));

        Set<ConstraintViolation<TaxCalculationRequestDTO>> violations = validator.validate(dto);
        assertEquals(0, violations.size(), "DTO válido não deve ter violações");
    }

    @Test
    public void testInvalidDTO() {
        TaxCalculationRequestDTO dto = new TaxCalculationRequestDTO();
        dto.setTipoImpostoId(null); // Campo obrigatório
        dto.setValorBase(null); // Campo obrigatório

        Set<ConstraintViolation<TaxCalculationRequestDTO>> violations = validator.validate(dto);
        assertEquals(2, violations.size(), "DTO inválido deve ter 2 violações");
    }
}