package com.impostoCalc.service.test;

import com.impostoCalc.dtos.Role;
import com.impostoCalc.dtos.request.UserLoginRequestDTO;
import com.impostoCalc.dtos.request.UserRegisterRequestDTO;
import com.impostoCalc.dtos.response.UserLoginResponseDTO;
import com.impostoCalc.dtos.response.UserRegisterResponseDTO;
import com.impostoCalc.model.User;
import com.impostoCalc.repository.UserRepository;
import com.impostoCalc.security.TokenService;
import com.impostoCalc.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private TokenService tokenService;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser_Success() {
        // Arrange
        UserRegisterRequestDTO requestDTO = new UserRegisterRequestDTO();
        requestDTO.setUsername("newUser");
        requestDTO.setPassword("securePassword");
        requestDTO.setRole(Role.USER);;

        when(userRepository.findByUsername("newUser")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("securePassword")).thenReturn("encodedPassword");

        User savedUser = new User(1, "newUser", "encodedPassword", com.impostoCalc.dtos.Role.USER);
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        // Act
        UserRegisterResponseDTO response = userService.registerUser(requestDTO);

        // Assert
        assertNotNull(response);
        assertEquals("newUser", response.getUsername());
        assertEquals(Role.USER, response.getRole());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testRegisterUser_UsernameAlreadyExists() {
        // Arrange
        UserRegisterRequestDTO requestDTO = new UserRegisterRequestDTO();
        requestDTO.setUsername("existingUser");
        requestDTO.setPassword("securePassword");
        requestDTO.setRole(Role.USER);

        when(userRepository.findByUsername("existingUser")).thenReturn(Optional.of(new User()));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.registerUser(requestDTO));
        assertEquals("Nome de usuário já existe.", exception.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testAuthenticateUser_Success() {
        // Arrange
        UserLoginRequestDTO requestDTO = new UserLoginRequestDTO();
        requestDTO.setUsername("validUser");
        requestDTO.setPassword("validPassword");

        User user = new User(1, "validUser", "encodedPassword", com.impostoCalc.dtos.Role.USER);
        when(userRepository.findByUsername("validUser")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("validPassword", "encodedPassword")).thenReturn(true);
        when(tokenService.generateToken(user)).thenReturn("mockedToken");

        // Act
        UserLoginResponseDTO response = userService.authenticateUser(requestDTO);

        // Assert
        assertNotNull(response);
        assertEquals("mockedToken", response.getToken());
        verify(tokenService, times(1)).generateToken(user);
    }

    @Test
    void testAuthenticateUser_UserNotFound() {
        // Arrange
        UserLoginRequestDTO requestDTO = new UserLoginRequestDTO();
        requestDTO.setUsername("invalidUser");
        requestDTO.setPassword("password");

        when(userRepository.findByUsername("invalidUser")).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.authenticateUser(requestDTO));
        assertEquals("Usuário não encontrado", exception.getMessage());
        verify(tokenService, never()).generateToken(any(User.class));
    }

    @Test
    void testAuthenticateUser_InvalidPassword() {
        // Arrange
        UserLoginRequestDTO requestDTO = new UserLoginRequestDTO();
        requestDTO.setUsername("validUser");
        requestDTO.setPassword("invalidPassword");

        User user = new User(1, "validUser", "encodedPassword", com.impostoCalc.dtos.Role.USER);
        when(userRepository.findByUsername("validUser")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("invalidPassword", "encodedPassword")).thenReturn(false);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.authenticateUser(requestDTO));
        assertEquals("Credenciais inválidas.", exception.getMessage());
        verify(tokenService, never()).generateToken(any(User.class));
    }
}