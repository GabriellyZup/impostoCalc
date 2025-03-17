package com.impostoCalc.controller.test;

import com.impostoCalc.controller.TaxTypeController;
import com.impostoCalc.dtos.TaxTypeRequestDTO;
import com.impostoCalc.dtos.TaxTypeResponseDTO;
import com.impostoCalc.service.TaxTypeService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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

    public TaxTypeControllerTest() {
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
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(taxTypes, result.getBody());
        verify(taxTypeService, times(1)).getAllTaxTypes();
    }

    @Test
    void testCreateTaxType() {
        // Arrange
        TaxTypeRequestDTO request = new TaxTypeRequestDTO();
        request.setNome("ICMS");
        request.setDescricao("Imposto sobre Circulação de Mercadorias e Serviços");
        request.setAliquota(BigDecimal.valueOf(18.0));

        TaxTypeResponseDTO response = new TaxTypeResponseDTO();
        response.setId(1);
        response.setNome("ICMS");
        response.setDescricao("Imposto sobre Circulação de Mercadorias e Serviços");
        response.setAliquota(BigDecimal.valueOf(18.0));

        when(taxTypeService.createTaxType(request)).thenReturn(response);

        // Act
        ResponseEntity<TaxTypeResponseDTO> result = taxTypeController.createTaxType(request);

        // Assert
        assertEquals(201, result.getStatusCodeValue());
        assertEquals(response, result.getBody());
        verify(taxTypeService, times(1)).createTaxType(request);
    }
}