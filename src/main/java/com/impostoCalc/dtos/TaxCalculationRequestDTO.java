package com.impostoCalc.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Schema(description = "Objeto de requisição para cálculo de imposto.")
public class TaxCalculationRequestDTO {

    @NotNull(message = "O ID do tipo de imposto é obrigatório.")
    @Schema(description = "ID do tipo de imposto a ser calculado.", example = "1", required = true)
    private Integer tipoImpostoId;

    @NotNull(message = "O valor base é obrigatório.")
    @Positive(message = "O valor base deve ser maior que zero.")
    @Schema(description = "Valor base para o cálculo do imposto.", example = "100.00", required = true)
    private BigDecimal valorBase;

    public Integer getTipoImpostoId() {
        return tipoImpostoId;
    }

    public void setTipoImpostoId(Integer tipoImpostoId) {
        this.tipoImpostoId = tipoImpostoId;
    }

    public BigDecimal getValorBase() {
        return valorBase;
    }

    public void setValorBase(BigDecimal valorBase) {
        this.valorBase = valorBase;
    }
}