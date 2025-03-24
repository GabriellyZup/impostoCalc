package com.impostoCalc.security.test;

import com.impostoCalc.dtos.Role;
import com.impostoCalc.model.User;
import com.impostoCalc.repository.UserRepository;
import com.impostoCalc.security.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TokenServiceTest {

    @InjectMocks
    private TokenService tokenService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(tokenService, "secret", "secret-test");
    }

    @Test
    void testGenerateToken() {
        User user = new User(1, "admin", "password", Role.ADMIN);
        assertDoesNotThrow(() -> tokenService.generateToken(user));
    }

    @Test
    void testValidateToken() {
        User user = new User(1, "admin", "password", Role.ADMIN);
        String token = tokenService.generateToken(user);
        assertEquals("admin", tokenService.validateToken(token));
    }
}