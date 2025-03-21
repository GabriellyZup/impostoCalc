// TokenServiceTest.java
package com.impostoCalc.config.test;

import com.impostoCalc.config.TokenService;
import com.impostoCalc.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class TokenServiceTest {

    private final TokenService tokenService = new TokenService();
    private final User user = new User("admin", "password", "ADMIN");

    @Test
    void generateToken_ShouldReturnValidToken() {
        // Arrange
        ReflectionTestUtils.setField(tokenService, "secret", "secretKey");

        // Act
        String token = tokenService.generateToken(user);

        // Assert
        assertNotNull(token);
        assertTrue(token.split("\\.").length == 3);
    }

    @Test
    void validateToken_WithValidToken_ShouldReturnUsername() {
        // Arrange
        ReflectionTestUtils.setField(tokenService, "secret", "secretKey");
        String token = tokenService.generateToken(user);

        // Act
        String username = tokenService.validateToken(token);

        // Assert
        assertEquals(user.getUsername(), username);
    }

    @Test
    void validateToken_WithInvalidToken_ShouldReturnNull() {
        // Act
        String result = tokenService.validateToken("invalidToken");

        // Assert
        assertNull(result);
    }
}