package com.impostoCalc.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority; // Import necessário para permissões
import org.springframework.security.core.authority.SimpleGrantedAuthority; // Import necessário para permissões
import org.springframework.security.core.userdetails.UserDetails; // Import necessário para integração com Spring Security

import java.util.Collection; // Import necessário para lista de permissões
import java.util.List; // Import necessário para lista de permissões

@Entity (name = "users")
@Table(name = "users")
@Schema(description = "Entidade que representa os usuários do sistema.")

public class User implements UserDetails { // Implementação da interface UserDetails
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Schema(description = "Papel do usuário no sistema.", example = "USER")
    private Role role;

    public User(String admin, String password, String admin1) {
    }//construtor criado para atender a classe de teste

    public User() {

    }

    public User(int i, String admin, String password, Role role) {
    }
    //construtor criado para atender a classe de teste

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getUsername() { // Sobrescrevendo o método da interface UserDetails
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() { // Sobrescrevendo o método da interface UserDetails
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    // Métodos da interface UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Retorna as permissões do usuário com base no papel (role)
        if (this.role == Role.ADMIN) { // Se o papel for ADMIN, retorna permissões de ADMIN e USER
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_USER")
            );
        } else { // Caso contrário, retorna apenas permissões de USER
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public boolean isAccountNonExpired() {
        // Indica que a conta não está expirada
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Indica que a conta não está bloqueada
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Indica que as credenciais não estão expiradas
        return true;
    }

    @Override
    public boolean isEnabled() {
        // Indica que a conta está habilitada
        return true;
    }
}