package com.impostoCalc.model.test;

import com.impostoCalc.model.Role;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoleTest {

    @Test
    void testRoleEnum() {
        // Assert
        assertEquals("ADMIN", Role.ADMIN.name());
        assertEquals("USER", Role.USER.name());
    }

    @Test
    void testRoleToGrantedAuthority() {
        // Convertendo Role para SimpleGrantedAuthority
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(Role.ADMIN.name());

        // Assert
        assertEquals("ADMIN", authority.getAuthority());
    }
}