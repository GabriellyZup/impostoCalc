package com.impostoCalc.repository.test;

import com.impostoCalc.dtos.Role;
import com.impostoCalc.model.User;
import com.impostoCalc.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @Test
    void testFindByUsername() {
        // Arrange
        User user = new User(1, "admin", "password", Role.ADMIN);
        when(userRepository.findByUsername("admin")).thenReturn(Optional.of(user));

        // Act
        Optional<User> result = userRepository.findByUsername("admin");

        // Assert
        assertTrue(result.isPresent());
    }
}