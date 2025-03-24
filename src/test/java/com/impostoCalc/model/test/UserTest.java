package com.impostoCalc.model.test;

import com.impostoCalc.dtos.Role;
import com.impostoCalc.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserTest {

    @Test
    void testUserModel() {
        // Arrange
        User user = new User();
        user.setId(1);
        user.setUsername("admin");
        user.setPassword("password");
        user.setRole(Role.ADMIN);

        // Assert
        assertEquals(1, user.getId());
        assertEquals("admin", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals(Role.ADMIN, user.getRole());
    }

    @Test
    void testUserConstructor() {
        // Arrange
        User user = new User(1, "admin", "password", Role.ADMIN);

        // Assert
        assertNotNull(user);
        assertEquals(1, user.getId());
        assertEquals("admin", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals(Role.ADMIN, user.getRole());
    }
}