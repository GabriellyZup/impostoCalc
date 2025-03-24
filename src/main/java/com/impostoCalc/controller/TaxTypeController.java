package com.impostoCalc.controller;

import com.impostoCalc.dtos.request.TaxTypeRequestDTO;
import com.impostoCalc.dtos.response.TaxTypeResponseDTO;
import com.impostoCalc.service.TaxTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos")
@Tag(name = "Tipos de Imposto", description = "Gerenciamento de tipos de impostos")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class TaxTypeController {

    private final TaxTypeService taxTypeService;

    @GetMapping
    @Operation(summary = "Listar todos os tipos de impostos")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<TaxTypeResponseDTO>> getAllTaxTypes() {
        return ResponseEntity.ok(taxTypeService.getAllTaxTypes());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter detalhes de um tipo de imposto pelo ID")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<TaxTypeResponseDTO> getTaxTypeById(@PathVariable Integer id) {
        return ResponseEntity.ok(taxTypeService.getTaxTypeById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Cadastrar novo tipo de imposto (ADMIN)")
    public ResponseEntity<TaxTypeResponseDTO> createTaxType(@RequestBody @Valid TaxTypeRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taxTypeService.createTaxType(requestDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Excluir tipo de imposto pelo ID (ADMIN)")
    public ResponseEntity<Void> deleteTaxType(@PathVariable Integer id) {
        taxTypeService.deleteTaxType(id);
        return ResponseEntity.noContent().build();
    }
}