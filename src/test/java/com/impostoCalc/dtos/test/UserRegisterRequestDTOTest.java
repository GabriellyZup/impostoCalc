package com.impostoCalc.dtos.test;

import com.impostoCalc.dtos.request.UserRegisterRequestDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserRegisterRequestDTOTest {

    @Test
    void testUserRequestDTO() {
        // Arrange
        UserRegisterRequestDTO userRegisterRequestDTO = new UserRegisterRequestDTO();
        userRegisterRequestDTO.setUsername("admin");
        userRegisterRequestDTO.setPassword("password");

        // Assert
        assertEquals("admin", userRegisterRequestDTO.getUsername());
        assertEquals("password", userRegisterRequestDTO.getPassword());
    }
}