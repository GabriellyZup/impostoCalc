package com.impostoCalc.controller.test;

import com.impostoCalc.controller.UserController;
import com.impostoCalc.dtos.UserRequestDTO;
import com.impostoCalc.dtos.UserResponseDTO;
import com.impostoCalc.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    public UserControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser() {
        // Arrange
        UserRequestDTO request = new UserRequestDTO();
        request.setUsername("admin");
        request.setPassword("password");

        UserResponseDTO response = new UserResponseDTO();
        response.setId(1);
        response.setUsername("admin");

        when(userService.registerUser(request)).thenReturn(response);

        // Act
        ResponseEntity<UserResponseDTO> result = userController.registerUser(request);

        // Assert
        assertEquals(201, result.getStatusCodeValue());
        assertEquals(response, result.getBody());
        verify(userService, times(1)).registerUser(request);
    }

    @Test
    void testLoginUser() {
        // Arrange
        UserRequestDTO request = new UserRequestDTO();
        request.setUsername("admin");
        request.setPassword("password");

        UserResponseDTO response = new UserResponseDTO();
        response.setId(1);
        response.setUsername("admin");

        when(userService.loginUser(request)).thenReturn(response);

        // Act
        ResponseEntity<UserResponseDTO> result = userController.loginUser(request);

        // Assert
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(response, result.getBody());
        verify(userService, times(1)).loginUser(request);
    }
}