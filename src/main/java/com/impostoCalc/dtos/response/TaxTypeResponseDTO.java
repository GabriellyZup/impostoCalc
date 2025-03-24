package com.impostoCalc.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Objeto de resposta para os detalhes de um tipo de imposto.")
public class TaxTypeResponseDTO {
    @Schema(description = "ID único do tipo de imposto.", example = "1")
    private Integer id;

    @Schema(description = "Nome do tipo de imposto.", example = "ICMS")
    private String nome;

    @Schema(description = "Descrição detalhada do tipo de imposto.", example = "Imposto sobre Circulação de Mercadorias e Serviços")
    private String descricao;

    @Schema(description = "Alíquota do imposto.", example = "18.00")
    private BigDecimal aliquota;

}