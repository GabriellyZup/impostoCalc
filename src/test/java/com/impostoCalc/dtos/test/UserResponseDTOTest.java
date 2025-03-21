package com.impostoCalc.dtos.test;

import com.impostoCalc.dtos.UserResponseDTO;
import com.impostoCalc.model.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserResponseDTOTest {

    @Test
    void testUserResponseDTO() {
        // Arrange
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(1);
        userResponseDTO.setUsername("admin");
        userResponseDTO.setRole(Role.ADMIN);
        userResponseDTO.setToken("mocked-token");

        // Assert
        assertEquals(1, userResponseDTO.getId());
        assertEquals("admin", userResponseDTO.getUsername());
        assertEquals(Role.ADMIN, userResponseDTO.getRole());
        assertEquals("mocked-token", userResponseDTO.getToken());
    }
}