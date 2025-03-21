package com.impostoCalc.dtos.test;

import com.impostoCalc.dtos.UserRequestDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserRequestDTOTest {

    @Test
    void testUserRequestDTO() {
        // Arrange
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setUsername("admin");
        userRequestDTO.setPassword("password");

        // Assert
        assertEquals("admin", userRequestDTO.getUsername());
        assertEquals("password", userRequestDTO.getPassword());
    }
}