package com.impostoCalc.controller.test;

import com.impostoCalc.controller.UserController;
import com.impostoCalc.dtos.Role;
import com.impostoCalc.dtos.request.UserLoginRequestDTO;
import com.impostoCalc.dtos.request.UserRegisterRequestDTO;
import com.impostoCalc.dtos.response.UserLoginResponseDTO;
import com.impostoCalc.dtos.response.UserRegisterResponseDTO;
import com.impostoCalc.security.TokenService;
import com.impostoCalc.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

class UserControllerTest {

    private UserController userController;

    @Mock
    private UserServiceImpl userServiceImpl;

    @Mock
    private TokenService tokenService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userController = new UserController(userServiceImpl, null, tokenService);
    }

    @Test
    void testRegisterUser() {
        // Arrange
        UserRegisterRequestDTO requestDTO = new UserRegisterRequestDTO();
        requestDTO.setUsername("usuario123");
        requestDTO.setPassword("senhaSegura");
        requestDTO.setRole(requestDTO.getRole());

        UserRegisterResponseDTO responseDTO = new UserRegisterResponseDTO(1, "usuario123", Role.USER);
        Mockito.when(userServiceImpl.registerUser(any(UserRegisterRequestDTO.class))).thenReturn(responseDTO);

        // Act
        ResponseEntity<UserRegisterResponseDTO> response = userController.registerUser(requestDTO);

        // Assert
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(responseDTO, response.getBody());
    }

    @Test
    void testLogin() {
        // Arrange
        UserLoginRequestDTO requestDTO = new UserLoginRequestDTO();
        requestDTO.setUsername("usuario123");
        requestDTO.setPassword("senhaSegura");

        UserLoginResponseDTO responseDTO = new UserLoginResponseDTO("mocked-jwt-token");
        Mockito.when(userServiceImpl.authenticateUser(any(UserLoginRequestDTO.class))).thenReturn(responseDTO);

        // Act
        ResponseEntity<UserLoginResponseDTO> response = userController.login(requestDTO);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(responseDTO, response.getBody());
    }
}