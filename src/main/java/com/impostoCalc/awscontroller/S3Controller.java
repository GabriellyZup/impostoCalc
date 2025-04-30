package com.impostoCalc.awscontroller;

import com.impostoCalc.awsservice.S3Service;
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
 * Controller para expor endpoints REST do S3.
 */
@RestController
@RequestMapping("/aws/s3")
@RequiredArgsConstructor
public class S3Controller {

    private final S3Service s3Service;

    /**
     * Endpoint para listar todos os buckets S3.
     * @return Lista de nomes dos buckets.
     */
    @GetMapping("/buckets")
    @Operation(
            summary = "Lista todos os buckets S3",
            description = "Retorna uma lista com os nomes dos buckets S3 disponíveis.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de buckets retornada com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(type = "string")),
                                    examples = @ExampleObject(value = "[\"meu-bucket-1\", \"meu-bucket-2\"]")
                            )
                    )
            }
    )
    public List<String> listBuckets() {
        return s3Service.listBuckets();
    }
}