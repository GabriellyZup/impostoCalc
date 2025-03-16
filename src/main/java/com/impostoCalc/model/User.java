package com.impostoCalc.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
@Schema(description = "Entidade que representa os usuários do sistema.")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do usuário.", example = "1")
    private Integer id;

    @Column(nullable = false, unique = true, length = 50)
    @Schema(description = "Nome de usuário único.", example = "usuario123")
    private String username;

    @Column(nullable = false)
    @Schema(description = "Senha do usuário.", example = "senhaSegura")
    private String password;

    @Column(nullable = false, length = 20)
    @Schema(description = "Papel do usuário no sistema.", example = "USER")
    private String role;

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