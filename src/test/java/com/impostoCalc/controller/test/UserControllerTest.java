package com.impostoCalc.controller.test;

import com.impostoCalc.dtos.UserRequestDTO;
import com.impostoCalc.dtos.UserResponseDTO;
import com.impostoCalc.model.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testRegisterUser_Success() {
        // Arrange
        UserRequestDTO request = new UserRequestDTO();
        request.setUsername("admin");
        request.setPassword("senhaSegura");
        request.setRole(Role.ADMIN);

        // Act
        ResponseEntity<UserResponseDTO> response = restTemplate.postForEntity(
                "/api/user/register",
                request,
                UserResponseDTO.class
        );

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
        assertEquals("admin", response.getBody().getUsername());
    }

    @Test
    void testLoginUser_Success() {
        // Registrar usuário primeiro
        testRegisterUser_Success();

        // Arrange
        UserRequestDTO request = new UserRequestDTO();
        request.setUsername("admin");
        request.setPassword("senhaSegura");

        // Act
        ResponseEntity<Map> response = restTemplate.postForEntity(
                "/api/user/login",
                request,
                Map.class
        );

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().get("token"));
    }
}