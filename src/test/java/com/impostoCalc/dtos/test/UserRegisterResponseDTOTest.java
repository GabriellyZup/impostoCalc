package com.impostoCalc.dtos.test;

import com.impostoCalc.dtos.Role;
import com.impostoCalc.dtos.response.UserRegisterResponseDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRegisterResponseDTOTest {

    @Test
    void testUserRegisterResponseDTO() {
        UserRegisterResponseDTO responseDTO = new UserRegisterResponseDTO(1, "usuario123", Role.USER);
        assertEquals(Role.USER, responseDTO.getRole());

//        // Act
//        UserRegisterResponseDTO responseDTO = new UserRegisterResponseDTO(id, username, Role.USER);
//
//        // Assert
//        assertEquals(id, responseDTO.getId());
//        assertEquals(username, responseDTO.getUsername());
//        assertEquals(role, responseDTO.getRole());
    }
}