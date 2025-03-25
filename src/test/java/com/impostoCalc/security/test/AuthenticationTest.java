//package com.impostoCalc.security.test;
//
//import com.impostoCalc.dtos.Role;
//import com.impostoCalc.dtos.request.UserLoginRequestDTO;
//import com.impostoCalc.dtos.request.UserRegisterRequestDTO;
//import com.impostoCalc.dtos.response.UserLoginResponseDTO;
//import com.impostoCalc.dtos.response.UserRegisterResponseDTO;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.http.*;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class AuthenticationTest {
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @LocalServerPort
//    private int port;
//
//    @Test
//    void testAccessPublicEndpoint() {
//        // 1. Registra o usuário ADMIN
//        UserRegisterRequestDTO registerRequest = new UserRegisterRequestDTO();
//        registerRequest.setUsername("admin");
//        registerRequest.setPassword("senhaSegura");
//        registerRequest.setRole(Role.ADMIN);
//        restTemplate.postForEntity(
//                "http://localhost:" + port + "/api/user/register",
//                registerRequest,
//                UserRegisterResponseDTO.class
//        );
//
//        // 2. Faz login
//        UserLoginRequestDTO loginRequest = new UserLoginRequestDTO("admin", "senhaSegura");
//        ResponseEntity<UserLoginResponseDTO> response = restTemplate.postForEntity(
//                "http://localhost:" + port + "/api/user/login",
//                loginRequest,
//                UserLoginResponseDTO.class
//        );
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//    }
//
//    @Test
//    void testAccessProtectedEndpointWithAuthentication() {
//        // 1. Registra o usuário ADMIN
//        UserRegisterRequestDTO registerRequest = new UserRegisterRequestDTO();
//        registerRequest.setUsername("admin");
//        registerRequest.setPassword("senhaSegura");
//        registerRequest.setRole(Role.ADMIN);
//        restTemplate.postForEntity(
//                "http://localhost:" + port + "/api/user/register",
//                registerRequest,
//                UserRegisterResponseDTO.class
//        );
//
//        // 2. Faz login
//        UserLoginRequestDTO loginRequest = new UserLoginRequestDTO("admin", "senhaSegura");
//        ResponseEntity<UserLoginResponseDTO> loginResponse = restTemplate.postForEntity(
//                "http://localhost:" + port + "/api/user/login",
//                loginRequest,
//                UserLoginResponseDTO.class
//        );
//        String token = loginResponse.getBody().getToken();
//
//        // 3. Acessa endpoint protegido com o token
//        HttpHeaders headers = new HttpHeaders();
//        headers.setBearerAuth(token);
//        RequestEntity<Void> request = RequestEntity.get(
//                "http://localhost:" + port + "/api/tipos"
//        ).headers(headers).build();
//
//        ResponseEntity<String> response = restTemplate.exchange(
//                request,
//                String.class
//        );
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//    }
//}