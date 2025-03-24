package com.impostoCalc.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Schema(description = "Objeto de requisição para criar ou atualizar um tipo de imposto.")
@Getter
@Setter
public class TaxTypeRequestDTO {

    @NotBlank(message = "O nome do tipo de imposto é obrigatório.")
    @Schema(description = "Nome do tipo de imposto.", example = "ICMS", required = true)
    private String nome;

    @NotBlank(message = "A descrição do tipo de imposto é obrigatória.")
    @Schema(description = "Descrição do tipo de imposto.", example = "Imposto sobre Circulação de Mercadorias e Serviços", required = true)
    private String descricao;

    @NotNull(message = "A alíquota é obrigatória.")
    @Positive(message = "A alíquota deve ser maior que zero.")
    @Max(value = 100, message = "A alíquota não pode ser maior que 100%.")
    @Schema(description = "Alíquota aplicada ao tipo de imposto.", example = "18.00", required = true)
    private BigDecimal aliquota;


}