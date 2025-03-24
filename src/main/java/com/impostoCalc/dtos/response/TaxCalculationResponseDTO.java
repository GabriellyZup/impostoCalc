package com.impostoCalc.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

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

    public String getTipoImposto() {
        return tipoImposto;
    }

    public void setTipoImposto(String tipoImposto) {
        this.tipoImposto = tipoImposto;
    }

    public BigDecimal getValorBase() {
        return valorBase;
    }

    public void setValorBase(BigDecimal valorBase) {
        this.valorBase = valorBase;
    }

    public BigDecimal getAliquota() {
        return aliquota;
    }

    public void setAliquota(BigDecimal aliquota) {
        this.aliquota = aliquota;
    }

    public BigDecimal getValorImposto() {
        return valorImposto;
    }

    public void setValorImposto(BigDecimal valorImposto) {
        this.valorImposto = valorImposto;
    }
}