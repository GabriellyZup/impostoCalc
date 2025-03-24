package com.impostoCalc.dtos.test;

import com.impostoCalc.dtos.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    @Test
    void testRoleEnum() {
        // Arrange & Act
        Role adminRole = Role.ADMIN;
        Role userRole = Role.USER;

        // Assert
        assertEquals("admin", adminRole.getRole());
        assertEquals("user", userRole.getRole());
    }
}