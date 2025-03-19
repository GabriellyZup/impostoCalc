package com.impostoCalc.dto.test;

import com.impostoCalc.dtos.UserResponseDTO;
import com.impostoCalc.model.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserResponseDTOTest {

    @Test
    public void testSettersAndGetters() {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(1);
        dto.setUsername("usuario123");
        dto.setRole(Role.valueOf("USER"));

        assertEquals(1, dto.getId());
        assertEquals("usuario123", dto.getUsername());
        assertEquals("USER", dto.getRole());
    }
}