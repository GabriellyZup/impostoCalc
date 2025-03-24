package com.impostoCalc.dtos.response;

import com.impostoCalc.dtos.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Objeto de resposta para o registro de um usuário.")
public class UserRegisterResponseDTO {

    @Schema(description = "ID único do usuário.", example = "1")
    private Integer id;

    @Schema(description = "Nome de usuário único.", example = "usuario123")
    private String username;

    @Schema(description = "Papel do usuário no sistema.", example = "USER")
    private Role role;

}