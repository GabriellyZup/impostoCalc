package com.impostoCalc.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Schema(description = "Objeto de requisição para criar ou atualizar um tipo de imposto.")

public class TaxTypeRequestDTO {

    @NotBlank(message = "O nome do tipo de imposto é obrigatório.")
    @Schema(description = "Nome do tipo de imposto.", example = "ICMS", required = true)
    private String nome;

    @NotBlank(message = "A descrição do tipo de imposto é obrigatória.")
    @Schema(description = "Descrição do tipo de imposto.", example = "Imposto sobre Circulação de Mercadorias e Serviços", required = true)
    private String descricao;

    @NotNull(message = "A alíquota é obrigatória.")
    @Positive(message = "A alíquota deve ser maior que zero.")
    @Schema(description = "Alíquota aplicada ao tipo de imposto.", example = "0.18", required = true)
    private BigDecimal aliquota;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getAliquota() {
        return aliquota;
    }

    public void setAliquota(BigDecimal aliquota) {
        this.aliquota = aliquota;
    }
}