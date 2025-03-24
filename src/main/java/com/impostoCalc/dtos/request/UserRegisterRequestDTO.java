package com.impostoCalc.dtos.request;

import com.impostoCalc.dtos.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.Data;

@Data

@Schema(description = "Objeto de requisição para registrar um novo usuário.")
@Getter
@Setter
public class UserRegisterRequestDTO {

    @NotBlank(message = "O nome de usuário é obrigatório.")
    @Size(min = 3, max = 50, message = "O nome de usuário deve ter entre 3 e 50 caracteres.")
    @Schema(description = "Nome de usuário para registro.", example = "usuario123", required = true)
    private String username;

    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 6, max = 100, message = "A senha deve ter entre 6 e 100 caracteres.")
    @Schema(description = "Senha do usuário.", example = "senhaSegura123", required = true)
    private String password;

    @Schema(description = "Papel do usuário no sistema.", example = "ADMIN", required = true)
    private Role role;



}