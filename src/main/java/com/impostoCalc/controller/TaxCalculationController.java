package com.impostoCalc.controller;

import com.impostoCalc.dtos.TaxCalculationRequestDTO;
import com.impostoCalc.dtos.TaxCalculationResponseDTO;
import com.impostoCalc.service.TaxCalculationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calculo")
@Tag(name = "Cálculo de Impostos", description = "Operações de cálculo de impostos")
@SecurityRequirement(name = "bearerAuth") // Integração com Swagger
public class TaxCalculationController {

    private final TaxCalculationService taxCalculationService;

    @Autowired
    public TaxCalculationController(TaxCalculationService taxCalculationService) {
        this.taxCalculationService = taxCalculationService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')") // Apenas ADMIN pode calcular
    @Operation(summary = "Calcular imposto (ADMIN)")
    public ResponseEntity<TaxCalculationResponseDTO> calculateTax(@RequestBody TaxCalculationRequestDTO request) {
        return ResponseEntity.ok(taxCalculationService.calculateTax(request));
    }
}