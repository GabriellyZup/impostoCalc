package com.impostoCalc.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Objeto de requisição para autenticar um usuário.")
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequestDTO {

    @NotBlank(message = "O nome de usuário é obrigatório.")
    @Schema(description = "Nome de usuário para autenticação.", example = "usuario123", required = true)
    private String username;

    @NotBlank(message = "A senha é obrigatória.")
    @Schema(description = "Senha do usuário.", example = "senhaSegura123", required = true)
    private String password;
}