package com.impostoCalc.dtos.test;

import com.impostoCalc.dtos.response.UserLoginResponseDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserLoginResponseDTOTest {

    @Test
    void testUserLoginResponseDTO() {
        // Arrange
        String token = "mocked-jwt-token";

        // Act
        UserLoginResponseDTO responseDTO = new UserLoginResponseDTO(token);

        // Assert
        assertEquals(token, responseDTO.getToken());
    }
}