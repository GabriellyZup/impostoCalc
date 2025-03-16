package com.impostoCalc.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Objeto de requisição para cálculo de imposto.")
public class TaxCalculationRequestDTO {

    @Schema(description = "ID do tipo de imposto.", example = "1", required = true)
    private Integer tipoImpostoId;

    @Schema(description = "Valor base para o cálculo do imposto.", example = "1000.00", required = true)
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