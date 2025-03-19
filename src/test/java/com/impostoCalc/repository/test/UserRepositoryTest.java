package com.impostoCalc.repository.test;

import com.impostoCalc.model.User;
import com.impostoCalc.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserRepositoryTest {

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByUsername_Success() {
        // Arrange
        String username = "admin";
        User user = new User();
        user.setUsername(username);

        when(mockUserRepository.findByUsername(username)).thenReturn(Optional.of(user));

        // Act
        Optional<User> result = mockUserRepository.findByUsername(username);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(username, result.get().getUsername());
        verify(mockUserRepository, times(1)).findByUsername(username);
    }

    @Test
    void testFindByUsername_NotFound() {
        // Arrange
        String username = "admin";

        when(mockUserRepository.findByUsername(username)).thenReturn(Optional.empty());

        // Act
        Optional<User> result = mockUserRepository.findByUsername(username);

        // Assert
        assertFalse(result.isPresent());
        verify(mockUserRepository, times(1)).findByUsername(username);
    }
}