package com.impostoCalc.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Objeto de resposta para o cálculo de imposto.")
public class TaxCalculationResponseDTO {

    @Schema(description = "Nome do tipo de imposto.", example = "ICMS")
    private String tipoImposto;

    @Schema(description = "Valor base utilizado no cálculo.", example = "1000.00")
    private BigDecimal valorBase;

    @Schema(description = "Alíquota do imposto.", example = "18.00")
    private BigDecimal aliquota;

    @Schema(description = "Valor calculado do imposto.", example = "180.00")
    private BigDecimal valorImposto;

}