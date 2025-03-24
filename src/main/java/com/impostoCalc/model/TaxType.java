package com.impostoCalc.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Entity
@Table(name = "tax_types")
@Schema(description = "Entidade que representa os tipos de impostos.")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaxType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do tipo de imposto.", example = "1")
    private Integer  id;

    @Column(nullable = false, unique = true, length = 50)
    @Schema(description = "Nome do tipo de imposto.", example = "ICMS")
    private String nome;

    @Column(columnDefinition = "TEXT")
    @Schema(description = "Descrição detalhada do tipo de imposto.", example = "Imposto sobre Circulação de Mercadorias e Serviços")
    private String descricao;

    @Column(nullable = false, precision = 10, scale = 2)
    @Schema(description = "Alíquota do imposto.", example = "18.00")
    private BigDecimal aliquota;
}