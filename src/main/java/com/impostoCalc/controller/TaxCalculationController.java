package com.impostoCalc.controller;

import com.impostoCalc.dtos.request.TaxCalculationRequestDTO;
import com.impostoCalc.dtos.response.TaxCalculationResponseDTO;
import com.impostoCalc.service.TaxCalculationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calculo")
@Tag(name = "Cálculo de Impostos", description = "Operações de cálculo de impostos")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class TaxCalculationController {

    private final TaxCalculationService taxCalculationService;

    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @Operation(summary = "Calcular imposto")
    public ResponseEntity<TaxCalculationResponseDTO> calculateTax(@RequestBody @Valid TaxCalculationRequestDTO request) {
        return ResponseEntity.ok(taxCalculationService.calculateTax(request));
    }
}