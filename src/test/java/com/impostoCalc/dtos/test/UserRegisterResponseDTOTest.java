package com.impostoCalc.dtos.test;

import com.impostoCalc.dtos.response.UserRegisterResponseDTO;
import com.impostoCalc.dtos.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserRegisterResponseDTOTest {

    @Test
    void testUserResponseDTO() {
        // Arrange
        UserRegisterResponseDTO userRegisterResponseDTO = new UserRegisterResponseDTO();
        userRegisterResponseDTO.setId(1);
        userRegisterResponseDTO.setUsername("admin");
        userRegisterResponseDTO.setRole(Role.ADMIN);
        userRegisterResponseDTO.setToken("mocked-token");

        // Assert
        assertEquals(1, userRegisterResponseDTO.getId());
        assertEquals("admin", userRegisterResponseDTO.getUsername());
        assertEquals(Role.ADMIN, userRegisterResponseDTO.getRole());
        assertEquals("mocked-token", userRegisterResponseDTO.getToken());
    }
}