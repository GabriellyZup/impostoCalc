package com.impostoCalc.controller;

import com.impostoCalc.dtos.TaxCalculationRequestDTO;
import com.impostoCalc.dtos.TaxCalculationResponseDTO;
import com.impostoCalc.service.TaxCalculationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calculo")
@Tag(name = "Cálculo de Impostos", description = "Endpoints para cálculo de impostos")
public class TaxCalculationController {

    @Autowired
    private TaxCalculationService taxCalculationService;

    @PostMapping
    @Operation(summary = "Calcular imposto", description = "Calcula o valor do imposto com base no tipo de imposto e no valor base fornecido.")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<TaxCalculationResponseDTO> calculateTax(@RequestBody TaxCalculationRequestDTO request) {
        TaxCalculationResponseDTO response = taxCalculationService.calculateTax(request);
        return ResponseEntity.ok(response);
    }
}