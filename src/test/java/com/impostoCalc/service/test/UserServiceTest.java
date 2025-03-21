package com.impostoCalc.service.test;

import com.impostoCalc.dtos.UserRequestDTO;
import com.impostoCalc.dtos.UserResponseDTO;
import com.impostoCalc.model.Role;
import com.impostoCalc.model.User;
import com.impostoCalc.repository.UserRepository;
import com.impostoCalc.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository, passwordEncoder);
    }

    @Test
    void testRegisterUser_Success() {
        // Arrange
        UserRequestDTO requestDTO = new UserRequestDTO();
        requestDTO.setUsername("usuario123");
        requestDTO.setPassword("senhaSegura");
        requestDTO.setRole(Role.USER);

        User user = new User();
        user.setId(1);
        user.setUsername("usuario123");
        user.setRole(Role.USER);

        Mockito.when(userRepository.findByUsername("usuario123")).thenReturn(null);
        Mockito.when(passwordEncoder.encode("senhaSegura")).thenReturn("senhaCodificada");
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        // Act
        UserResponseDTO result = userService.registerUser(requestDTO);

        // Assert
        assertNotNull(result);
        assertEquals("usuario123", result.getUsername());
    }

    @Test
    void testRegisterUser_UsernameExists() {
        // Arrange
        UserRequestDTO requestDTO = new UserRequestDTO();
        requestDTO.setUsername("usuario123");

        Mockito.when(userRepository.findByUsername("usuario123")).thenReturn(new User());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.registerUser(requestDTO));
        assertEquals("Nome de usuário já existe.", exception.getMessage());
    }
}