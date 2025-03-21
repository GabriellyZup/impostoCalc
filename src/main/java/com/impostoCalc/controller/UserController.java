package com.impostoCalc.controller;

import com.impostoCalc.config.TokenService;
import com.impostoCalc.dtos.UserRequestDTO;
import com.impostoCalc.dtos.UserResponseDTO;
import com.impostoCalc.model.User;
import com.impostoCalc.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User", description = "Gerenciamento de Usuários")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/register")
    @Operation(summary = "Registrar um novo usuário")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRequestDTO requestDTO) {
        UserResponseDTO user = userService.registerUser(requestDTO);
        return ResponseEntity.status(201).body(user);
    }

    @PostMapping("/login")
    @Operation(summary = "Autenticar um usuário")
    public ResponseEntity<?> loginUser(@RequestBody UserRequestDTO requestDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(requestDTO.getUsername(), requestDTO.getPassword());
        var auth = authenticationManager.authenticate(usernamePassword);

//        var token = tokenService.generateToken((User) auth.getPrincipal());
        UserResponseDTO userResponse = userService.loginUser(requestDTO);
//        userResponse.setToken(token); // Adiciona o token ao DTO de resposta

        return ResponseEntity.ok("ok");
    }
}