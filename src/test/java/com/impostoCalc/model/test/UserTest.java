package com.impostoCalc.model.test;

import com.impostoCalc.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserGettersAndSetters() {
        // Arrange
        User user = new User();
        Integer id = 1;
        String username = "usuario123";
        String password = "senhaSegura";
        String role = "USER";

        // Act
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);

        // Assert
        assertEquals(id, user.getId());
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(role, user.getRole());
    }

    @Test
    void testUserDefaultValues() {
        // Arrange
        User user = new User();

        // Assert
        assertNull(user.getId());
        assertNull(user.getUsername());
        assertNull(user.getPassword());
        assertNull(user.getRole());
    }
}