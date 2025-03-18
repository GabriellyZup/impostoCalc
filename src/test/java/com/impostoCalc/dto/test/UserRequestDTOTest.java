package com.impostoCalc.dto.test;

import com.impostoCalc.dtos.UserRequestDTO;
import com.impostoCalc.model.Role;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserRequestDTOTest {

    private final Validator validator;

    public UserRequestDTOTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Test
    public void testValidDTO() {
        UserRequestDTO dto = new UserRequestDTO();
        dto.setUsername("usuario123");
        dto.setPassword("senhaSegura");
        dto.setRole(Role.USER); // Use o enum diretamente
        Set<ConstraintViolation<UserRequestDTO>> violations = validator.validate(dto);
        assertEquals(0, violations.size(), "DTO válido não deve ter violações");
    }

    @Test
    public void testInvalidDTO() {
        UserRequestDTO dto = new UserRequestDTO();
        dto.setUsername(null); // Campo obrigatório
        dto.setPassword(null); // Campo obrigatório
        dto.setRole(null); // Campo obrigatório
        Set<ConstraintViolation<UserRequestDTO>> violations = validator.validate(dto);
        assertEquals(3, violations.size(), "DTO inválido deve ter 3 violações");
    }
}