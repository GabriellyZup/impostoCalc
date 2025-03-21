package com.impostoCalc.controller.test;

import com.impostoCalc.controller.TaxTypeController;
import com.impostoCalc.dtos.TaxTypeRequestDTO;
import com.impostoCalc.dtos.TaxTypeResponseDTO;
import com.impostoCalc.service.TaxTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TaxTypeControllerTest {

    @InjectMocks
    private TaxTypeController taxTypeController;

    @Mock
    private TaxTypeService taxTypeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTaxTypes() {
        // Arrange
        TaxTypeResponseDTO taxType1 = new TaxTypeResponseDTO();
        taxType1.setId(1);
        taxType1.setNome("ICMS");
        taxType1.setDescricao("Imposto sobre Circulação de Mercadorias e Serviços");
        taxType1.setAliquota(BigDecimal.valueOf(18.0));

        TaxTypeResponseDTO taxType2 = new TaxTypeResponseDTO();
        taxType2.setId(2);
        taxType2.setNome("ISS");
        taxType2.setDescricao("Imposto sobre Serviços");
        taxType2.setAliquota(BigDecimal.valueOf(5.0));

        List<TaxTypeResponseDTO> taxTypes = Arrays.asList(taxType1, taxType2);
        when(taxTypeService.getAllTaxTypes()).thenReturn(taxTypes);

        // Act
        ResponseEntity<List<TaxTypeResponseDTO>> result = taxTypeController.getAllTaxTypes();

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(taxTypes, result.getBody());
        verify(taxTypeService, times(1)).getAllTaxTypes();
    }

    @Test
    void testGetTaxTypeById() {
        // Arrange
        TaxTypeResponseDTO taxType = new TaxTypeResponseDTO();
        taxType.setId(1);
        taxType.setNome("ICMS");
        taxType.setDescricao("Imposto sobre Circulação de Mercadorias e Serviços");
        taxType.setAliquota(BigDecimal.valueOf(18.0));

        when(taxTypeService.getTaxTypeById(1)).thenReturn(taxType);

        // Act
        ResponseEntity<TaxTypeResponseDTO> result = taxTypeController.getTaxTypeById(1);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(taxType, result.getBody());
        verify(taxTypeService, times(1)).getTaxTypeById(1);
    }

    @Test
    void testCreateTaxType() {
        // Arrange
        TaxTypeRequestDTO request = new TaxTypeRequestDTO();
        request.setNome("IPI");
        request.setDescricao("Imposto sobre Produtos Industrializados");
        request.setAliquota(BigDecimal.valueOf(12.0));

        TaxTypeResponseDTO response = new TaxTypeResponseDTO();
        response.setId(3);
        response.setNome("IPI");
        response.setDescricao("Imposto sobre Produtos Industrializados");
        response.setAliquota(BigDecimal.valueOf(12.0));

        when(taxTypeService.createTaxType(request)).thenReturn(response);

        // Act
        ResponseEntity<TaxTypeResponseDTO> result = taxTypeController.createTaxType(request);

        // Assert
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(taxTypeService, times(1)).createTaxType(request);
    }

    @Test
    void testDeleteTaxType() {
        // Arrange
        doNothing().when(taxTypeService).deleteTaxType(1);

        // Act
        ResponseEntity<Void> result = taxTypeController.deleteTaxType(1);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(taxTypeService, times(1)).deleteTaxType(1);
    }
}