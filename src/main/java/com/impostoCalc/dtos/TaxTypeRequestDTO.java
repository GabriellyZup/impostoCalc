package com.impostoCalc.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

@Schema(description = "Objeto de requisição para criar ou atualizar um tipo de imposto.")

public class TaxTypeRequestDTO {

    @Schema(description = "Nome do tipo de imposto.", example = "ICMS", required = true)
    private String nome;

    @Schema(description = "Descrição detalhada do tipo de imposto.", example = "Imposto sobre Circulação de Mercadorias e Serviços")
    private String descricao;

    @Schema(description = "Alíquota do imposto.", example = "18.00", required = true)
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