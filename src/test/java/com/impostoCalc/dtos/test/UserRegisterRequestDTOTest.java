package com.impostoCalc.dtos.test;

import com.impostoCalc.dtos.Role;
import com.impostoCalc.dtos.request.UserRegisterRequestDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRegisterRequestDTOTest {

    @Test
    void testUserRegisterRequestDTO() {
        // Arrange
        UserRegisterRequestDTO requestDTO = new UserRegisterRequestDTO();
        String username = "usuario123";
        String password = "senhaSegura123";
        Role role = Role.ADMIN;

        // Act
        requestDTO.setUsername(username);
        requestDTO.setPassword(password);
        requestDTO.setRole(Role.ADMIN);

        // Assert
        assertEquals(username, requestDTO.getUsername());
        assertEquals(password, requestDTO.getPassword());
        assertEquals(role, requestDTO.getRole());
    }
}