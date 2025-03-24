package com.impostoCalc.dtos.test;

import com.impostoCalc.dtos.request.UserLoginRequestDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserLoginRequestDTOTest {

    @Test
    void testUserLoginRequestDTO() {
        // Arrange
        UserLoginRequestDTO requestDTO = new UserLoginRequestDTO();
        String username = "usuario123";
        String password = "senhaSegura123";

        // Act
        requestDTO.setUsername(username);
        requestDTO.setPassword(password);

        // Assert
        assertEquals(username, requestDTO.getUsername());
        assertEquals(password, requestDTO.getPassword());
    }
}