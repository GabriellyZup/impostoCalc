package com.impostoCalc.awscontroller;

import com.impostoCalc.awsservice.Ec2Service;
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
 * Controller para expor endpoints REST do EC2.
 */
@RestController
@RequestMapping("/aws/ec2")
@RequiredArgsConstructor
public class Ec2Controller {

    private final Ec2Service ec2Service;

    /**
     * Endpoint para listar todos os IDs das instâncias EC2.
     * @return Lista de instanceId das instâncias EC2.
     */
    @GetMapping("/instances")
    @Operation(
            summary = "Lista todas as instâncias EC2",
            description = "Retorna uma lista com os IDs das instâncias EC2 disponíveis.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de instâncias retornada com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(type = "string")),
                                    examples = @ExampleObject(value = "[\"i-1234567890abcdef0\", \"i-0987654321fedcba0\"]")
                            )
                    )
            }
    )
    public List<String> listInstances() {
        return ec2Service.listInstances();
    }
}