package com.impostoCalc.controller.test;

import com.impostoCalc.dtos.TaxTypeRequestDTO;
import com.impostoCalc.dtos.TaxTypeResponseDTO;
import com.impostoCalc.dtos.UserRequestDTO;
import com.impostoCalc.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class TaxTypeControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private String adminToken;

    @BeforeEach
    void setUp() {
        // Registrar e logar como ADMIN
        UserRequestDTO registerRequest = new UserRequestDTO();
        registerRequest.setUsername("admin");
        registerRequest.setPassword("senhaSegura");
        registerRequest.setRole(Role.ADMIN);
        ResponseEntity<Void> registerResponse = restTemplate.postForEntity("/api/user/register", registerRequest, Void.class);
        assertEquals(HttpStatus.CREATED, registerResponse.getStatusCode(), "Falha ao registrar o usuário admin");

        UserRequestDTO loginRequest = new UserRequestDTO();
        loginRequest.setUsername("admin");
        loginRequest.setPassword("senhaSegura");
        ResponseEntity<Map> loginResponse = restTemplate.postForEntity(
                "/api/user/login",
                loginRequest,
                Map.class
        );
        assertEquals(HttpStatus.OK, loginResponse.getStatusCode(), "Falha ao fazer login como admin");
        assertNotNull(loginResponse.getBody());
        assertNotNull(loginResponse.getBody().get("token"), "Token não retornado no login");
        adminToken = (String) loginResponse.getBody().get("token");
    }

    @Test
    void testCreateTaxType_AsAdmin() {
        // Arrange
        TaxTypeRequestDTO request = new TaxTypeRequestDTO();
        request.setNome("ICMS");
        request.setDescricao("Imposto sobre Circulação de Mercadorias e Serviços");
        request.setAliquota(BigDecimal.valueOf(18.0));
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(adminToken);
        HttpEntity<TaxTypeRequestDTO> entity = new HttpEntity<>(request, headers);

        // Act
        ResponseEntity<TaxTypeResponseDTO> response = restTemplate.exchange(
                "/api/tipos",
                HttpMethod.POST,
                entity,
                TaxTypeResponseDTO.class
        );

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
    }

    @Test
    void testGetAllTaxTypes_AsUser() {
        // Arrange
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(adminToken); // Usando token de ADMIN (pode criar um USER separado se necessário)
        HttpEntity<?> entity = new HttpEntity<>(headers);

        // Act
        ResponseEntity<List> response = restTemplate.exchange(
                "/api/tipos",
                HttpMethod.GET,
                entity,
                List.class
        );

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}