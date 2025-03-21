package com.impostoCalc.dtos;

import com.impostoCalc.model.Role;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Objeto de resposta para os detalhes de um usuário.")
public class UserResponseDTO {

    @Schema(description = "ID único do usuário.", example = "1")
    private Integer id;

    @Schema(description = "Nome de usuário único.", example = "usuario123")
    private String username;

    @Schema(description = "Papel do usuário no sistema.", example = "USER")
    private Role role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}