package com.impostoCalc.service.test;

import com.impostoCalc.dtos.Role;
import com.impostoCalc.model.User;
import com.impostoCalc.repository.UserRepository;
import com.impostoCalc.security.CustomUserDetailsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @Test
    void testLoadUserByUsername() {
        // Arrange
        User user = new User(1, "admin", "password", Role.ADMIN);
        when(userRepository.findByUsername("admin")).thenReturn(Optional.of(user));
        // Act
        UserDetails userDetails = customUserDetailsService.loadUserByUsername("admin");
        // Assert
        assertEquals("admin", userDetails.getUsername());
    }

    @Test
    void testLoadUserByUsernameNotFound() {
        // Arrange
        String username = "unknown";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> customUserDetailsService.loadUserByUsername(username));
    }
}