package com.impostoCalc.controller;

import com.impostoCalc.dtos.TaxTypeRequestDTO;
import com.impostoCalc.dtos.TaxTypeResponseDTO;
import com.impostoCalc.service.TaxTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos")
@Tag(name = "TaxType", description = "Gerenciamento de Tipos de Impostos")
public class TaxTypeController {

    private final TaxTypeService taxTypeService;

    @Autowired
    public TaxTypeController(TaxTypeService taxTypeService) {
        this.taxTypeService = taxTypeService;
    }

    @GetMapping
    @Operation(summary = "Listar todos os tipos de impostos")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<TaxTypeResponseDTO>> getAllTaxTypes() {
        List<TaxTypeResponseDTO> taxTypes = taxTypeService.getAllTaxTypes();
        return ResponseEntity.ok(taxTypes);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter detalhes de um tipo de imposto pelo ID")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<TaxTypeResponseDTO> getTaxTypeById(@PathVariable Integer id) {
        TaxTypeResponseDTO taxType = taxTypeService.getTaxTypeById(id);
        return ResponseEntity.ok(taxType);
    }

    @PostMapping
    @Operation(summary = "Cadastrar um novo tipo de imposto")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TaxTypeResponseDTO> createTaxType(@RequestBody TaxTypeRequestDTO requestDTO) {
        TaxTypeResponseDTO createdTaxType = taxTypeService.createTaxType(requestDTO);
        return ResponseEntity.status(201).body(createdTaxType);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um tipo de imposto pelo ID")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteTaxType(@PathVariable Integer id) {
        taxTypeService.deleteTaxType(id);
        return ResponseEntity.noContent().build();
    }
}