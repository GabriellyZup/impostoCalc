package com.impostoCalc.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Objeto de requisição para registrar ou autenticar um usuário.")
public class UserRequestDTO {

    @Schema(description = "Nome de usuário único.", example = "usuario123", required = true)
    private String username;

    @Schema(description = "Senha do usuário.", example = "senhaSegura", required = true)
    private String password;

    @Schema(description = "Papel do usuário no sistema.", example = "USER", required = true)
    private String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}