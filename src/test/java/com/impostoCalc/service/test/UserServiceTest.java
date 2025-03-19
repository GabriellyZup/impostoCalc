package com.impostoCalc.service.test;

import com.impostoCalc.dtos.UserRequestDTO;
import com.impostoCalc.dtos.UserResponseDTO;
import com.impostoCalc.model.Role;
import com.impostoCalc.model.User;
import com.impostoCalc.repository.UserRepository;
import com.impostoCalc.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser_Success() {
        UserRequestDTO requestDTO = new UserRequestDTO();
        requestDTO.setUsername("usuario123");
        requestDTO.setPassword("senhaSegura");
        requestDTO.setRole(Role.USER);

        User user = new User();
        user.setId(1);
        user.setUsername("usuario123");
        user.setPassword("hashedPassword");
        user.setRole(Role.USER);

        when(userRepository.findByUsername("usuario123")).thenReturn(null);
        when(passwordEncoder.encode("senhaSegura")).thenReturn("hashedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserResponseDTO responseDTO = userService.registerUser(requestDTO);

        assertNotNull(responseDTO);
        assertEquals("usuario123", responseDTO.getUsername());
        assertEquals(Role.USER, responseDTO.getRole());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testRegisterUser_UsernameAlreadyExists() {
        UserRequestDTO requestDTO = new UserRequestDTO();
        requestDTO.setUsername("usuario123");
        requestDTO.setPassword("senhaSegura");
        requestDTO.setRole(Role.USER);

        when(userRepository.findByUsername("usuario123")).thenReturn(Optional.of(new User()));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.registerUser(requestDTO);
        });

        assertEquals("Nome de usuário já existe.", exception.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testRegisterUser_InvalidRole() {
        UserRequestDTO requestDTO = new UserRequestDTO();
        requestDTO.setUsername("usuario123");
        requestDTO.setPassword("senhaSegura");
        requestDTO.setRole(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.registerUser(requestDTO);
        });

        assertEquals("Papel inválido. Deve ser USER ou ADMIN.", exception.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testLoginUser_Success() {
        UserRequestDTO requestDTO = new UserRequestDTO();
        requestDTO.setUsername("usuario123");
        requestDTO.setPassword("senhaSegura");

        User user = new User();
        user.setId(1);
        user.setUsername("usuario123");
        user.setPassword("hashedPassword");
        user.setRole(Role.USER);

        when(userRepository.findByUsername("usuario123")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("senhaSegura", "hashedPassword")).thenReturn(true);

        UserResponseDTO responseDTO = userService.loginUser(requestDTO);

        assertNotNull(responseDTO);
        assertEquals("usuario123", responseDTO.getUsername());
        assertEquals(Role.USER, responseDTO.getRole());
    }

    @Test
    void testLoginUser_InvalidCredentials() {
        UserRequestDTO requestDTO = new UserRequestDTO();
        requestDTO.setUsername("usuario123");
        requestDTO.setPassword("senhaIncorreta");

        User user = new User();
        user.setId(1);
        user.setUsername("usuario123");
        user.setPassword("hashedPassword");
        user.setRole(Role.USER);

        when(userRepository.findByUsername("usuario123")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("senhaIncorreta", "hashedPassword")).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.loginUser(requestDTO);
        });

        assertEquals("Credenciais inválidas.", exception.getMessage());
    }
}