package com.impostoCalc.dto.test;

import com.impostoCalc.dtos.UserResponseDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserResponseDTOTest {

    @Test
    public void testSettersAndGetters() {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(1);
        dto.setUsername("usuario123");
        dto.setRole("USER");

        assertEquals(1, dto.getId());
        assertEquals("usuario123", dto.getUsername());
        assertEquals("USER", dto.getRole());
    }
}