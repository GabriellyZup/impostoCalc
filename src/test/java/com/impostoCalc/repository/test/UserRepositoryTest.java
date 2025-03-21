package com.impostoCalc.repository.test;

import com.impostoCalc.model.Role;
import com.impostoCalc.model.User;
import com.impostoCalc.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindByUsername() {
        // Arrange
        User user = new User();
        user.setUsername("usuario123");
        user.setPassword("senhaSegura");
        user.setRole(Role.USER);
        userRepository.save(user);

        // Act
        User result = userRepository.findByUsername("usuario123");

        // Assert
        assertNotNull(result);
        assertEquals("usuario123", result.getUsername());
    }
}