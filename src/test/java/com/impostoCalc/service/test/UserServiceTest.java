package com.impostoCalc.service.test;

import com.impostoCalc.dtos.UserRequestDTO;
import com.impostoCalc.dtos.UserResponseDTO;
import com.impostoCalc.model.User;
import com.impostoCalc.repository.UserRepository;
import com.impostoCalc.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser_Success() {
        // Arrange
        UserRequestDTO request = new UserRequestDTO();
        request.setUsername("admin");
        request.setPassword("password");
        request.setRole("ADMIN");

        User user = new User();
        user.setId(1);
        user.setUsername("admin");

        when(userRepository.findByUsername("admin")).thenReturn(null);
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        UserResponseDTO result = userService.registerUser(request);

        // Assert
        assertNotNull(result);
        assertEquals("admin", result.getUsername());
        verify(userRepository, times(1)).findByUsername("admin");
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testRegisterUser_UsernameExists() {
        // Arrange
        UserRequestDTO request = new UserRequestDTO();
        request.setUsername("admin");

        when(userRepository.findByUsername("admin")).thenReturn(new User());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.registerUser(request);
        });

        assertEquals("Nome de usuário já existe.", exception.getMessage());
        verify(userRepository, times(1)).findByUsername("admin");
    }
}