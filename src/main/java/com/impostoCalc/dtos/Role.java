package com.impostoCalc.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "Papéis disponíveis para os usuários.")
public enum Role {
    @Schema(description = "Administrador do sistema.", example = "ADMIN")
    ADMIN("admin"),

    @Schema(description = "Usuário comum.", example = "USER")
    USER("user");

    private final String role;

    Role(String role) {
        this.role = role;
    }
}

