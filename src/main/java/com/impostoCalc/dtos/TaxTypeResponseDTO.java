package com.impostoCalc.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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