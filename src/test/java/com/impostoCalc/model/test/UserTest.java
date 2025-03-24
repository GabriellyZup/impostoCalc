package com.impostoCalc.model.test;

import com.impostoCalc.dtos.Role;
import com.impostoCalc.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserDetails() {
        // Arrange
        Integer id = 1;
        String username = "usuario123";
        String password = "senhaSegura";
        Role role = Role.USER;

        // Act
        User user = new User(id, username, password, role);
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        // Assert
        assertEquals(id, user.getId());
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals("ROLE_USER", authorities.iterator().next().getAuthority());
        assertTrue(user.isAccountNonExpired());
        assertTrue(user.isAccountNonLocked());
        assertTrue(user.isCredentialsNonExpired());
        assertTrue(user.isEnabled());
    }
}