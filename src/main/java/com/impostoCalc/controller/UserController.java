package com.impostoCalc.controller;

import com.impostoCalc.dtos.UserRequestDTO;
import com.impostoCalc.dtos.UserResponseDTO;
import com.impostoCalc.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User", description = "Gerenciamento de Usuários")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @Operation(summary = "Registrar um novo usuário")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRequestDTO requestDTO) {
        UserResponseDTO user = userService.registerUser(requestDTO);
        return ResponseEntity.status(201).body(user);
    }

    @PostMapping("/login")
    @Operation(summary = "Autenticar um usuário")
    public ResponseEntity<UserResponseDTO> loginUser(@RequestBody UserRequestDTO requestDTO) {
        UserResponseDTO user = userService.loginUser(requestDTO);
        return ResponseEntity.ok(user);
    }
}