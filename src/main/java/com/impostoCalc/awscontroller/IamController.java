package com.impostoCalc.awscontroller;

import com.impostoCalc.awsservice.IamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller para expor endpoints REST do IAM.
 */
@RestController
@RequestMapping("/aws/iam")
@RequiredArgsConstructor
public class IamController {

    private final IamService iamService;

    /**
     * Endpoint para listar todos os nomes de usuários IAM.
     * @return Lista de nomes de usuários IAM.
     */
    @GetMapping("/users")
    @Operation(
            summary = "Lista todos os usuários IAM",
            description = "Retorna uma lista com os nomes dos usuários IAM.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de usuários retornada com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(type = "string")),
                                    examples = @ExampleObject(value = "[\"usuario1\", \"usuario2\"]")
                            )
                    )
            }
    )
    public List<String> listUsers() {
        return iamService.listUsers();
    }
}