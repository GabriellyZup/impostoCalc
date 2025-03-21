package com.impostoCalc.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tax_types")
@Schema(description = "Entidade que representa os tipos de impostos.")
public class TaxType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do tipo de imposto.", example = "1")
    private Integer id;

    @Column(nullable = false, unique = true, length = 50)
    @Schema(description = "Nome do tipo de imposto.", example = "ICMS")
    private String nome;

    @Column(columnDefinition = "TEXT")
    @Schema(description = "Descrição detalhada do tipo de imposto.", example = "Imposto sobre Circulação de Mercadorias e Serviços")
    private String descricao;

    @Column(nullable = false, precision = 10, scale = 2)
    @Schema(description = "Alíquota do imposto.", example = "18.00")
    private BigDecimal aliquota;

    public TaxType(int i, String icms, String s, BigDecimal bigDecimal) {
    }

    public TaxType() {

    }
    //construtor criado para atender a classe de test

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