package com.impostoCalc.security.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthenticationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void testAccessPublicEndpoint() {
        String url = "http://localhost:" + port + "/api/user/login";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testAccessProtectedEndpointWithoutAuthentication() {
        String url = "http://localhost:" + port + "/api/tipos";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    void testAccessProtectedEndpointWithAuthentication() {
        String url = "http://localhost:" + port + "/api/tipos";

        // Simula autenticação básica (ou JWT, dependendo da implementação)
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("usuario123", "senhaSegura"); // Substitua pelos dados reais

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}