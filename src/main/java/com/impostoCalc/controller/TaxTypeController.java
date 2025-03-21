package com.impostoCalc.controller;

import com.impostoCalc.dtos.TaxTypeRequestDTO;
import com.impostoCalc.dtos.TaxTypeResponseDTO;
import com.impostoCalc.service.TaxTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos")
@Tag(name = "Tipos de Imposto", description = "Gerenciamento de tipos de impostos")
@SecurityRequirement(name = "bearerAuth") // Integração com Swagger
public class TaxTypeController {

    private final TaxTypeService taxTypeService;

    @Autowired
    public TaxTypeController(TaxTypeService taxTypeService) {
        this.taxTypeService = taxTypeService;
    }

    @GetMapping
    @Operation(summary = "Listar todos os tipos de impostos")
    public ResponseEntity<List<TaxTypeResponseDTO>> getAllTaxTypes() {
        return ResponseEntity.ok(taxTypeService.getAllTaxTypes());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter detalhes de um tipo de imposto pelo ID")
    public ResponseEntity<TaxTypeResponseDTO> getTaxTypeById(@PathVariable Integer id) {
        return ResponseEntity.ok(taxTypeService.getTaxTypeById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')") // Apenas ADMIN pode cadastrar
    @Operation(summary = "Cadastrar novo tipo de imposto (ADMIN)")
    public ResponseEntity<TaxTypeResponseDTO> createTaxType(@RequestBody TaxTypeRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taxTypeService.createTaxType(requestDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')") // Apenas ADMIN pode excluir
    @Operation(summary = "Excluir tipo de imposto pelo ID (ADMIN)")
    public ResponseEntity<Void> deleteTaxType(@PathVariable Integer id) {
        taxTypeService.deleteTaxType(id);
        return ResponseEntity.noContent().build();
    }
}