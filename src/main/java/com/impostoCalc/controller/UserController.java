package com.impostoCalc.controller;

import com.impostoCalc.dtos.request.UserLoginRequestDTO;
import com.impostoCalc.dtos.response.UserLoginResponseDTO;
import com.impostoCalc.dtos.response.UserRegisterResponseDTO;
import com.impostoCalc.dtos.request.UserRegisterRequestDTO;

import com.impostoCalc.security.TokenService;
import com.impostoCalc.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User", description = "Gerenciamento de Usuários")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userServiceImpl;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/register")
    @Operation(summary = "Registrar um novo usuário")
    public ResponseEntity<UserRegisterResponseDTO> registerUser(@RequestBody @Valid UserRegisterRequestDTO requestDTO) {
        UserRegisterResponseDTO userRegisterResponseDTO = userServiceImpl.registerUser(requestDTO);

        if (userRegisterResponseDTO != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(userRegisterResponseDTO);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/login")
    @Operation(summary = "Autenticar um usuário")
    public ResponseEntity<UserLoginResponseDTO> login(@RequestBody @Valid UserLoginRequestDTO userLoginRequestDTO){
        UserLoginResponseDTO userLoginResponseDTO = userServiceImpl.authenticateUser(userLoginRequestDTO);

        if (userLoginResponseDTO != null){
            return ResponseEntity.ok(userLoginResponseDTO);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}