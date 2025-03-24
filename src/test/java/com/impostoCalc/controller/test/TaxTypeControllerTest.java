package com.impostoCalc.controller.test;

import com.impostoCalc.controller.TaxTypeController;
import com.impostoCalc.dtos.request.TaxTypeRequestDTO;
import com.impostoCalc.dtos.response.TaxTypeResponseDTO;
import com.impostoCalc.service.TaxTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

class TaxTypeControllerTest {

    private TaxTypeController taxTypeController;

    @Mock
    private TaxTypeService taxTypeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        taxTypeController = new TaxTypeController(taxTypeService);
    }

    @Test
    void testGetAllTaxTypes() {
        // Arrange
        TaxTypeResponseDTO taxType1 = new TaxTypeResponseDTO(1, "ICMS", "Imposto sobre Circulação de Mercadorias e Serviços", BigDecimal.valueOf(18.0));
        TaxTypeResponseDTO taxType2 = new TaxTypeResponseDTO(2, "ISS", "Imposto sobre Serviços", BigDecimal.valueOf(5.0));
        Mockito.when(taxTypeService.getAllTaxTypes()).thenReturn(List.of(taxType1, taxType2));

        // Act
        ResponseEntity<List<TaxTypeResponseDTO>> response = taxTypeController.getAllTaxTypes();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testGetTaxTypeById() {
        // Arrange
        Integer id = 1;
        TaxTypeResponseDTO taxType = new TaxTypeResponseDTO(id, "ICMS", "Imposto sobre Circulação de Mercadorias e Serviços", BigDecimal.valueOf(18.0));
        Mockito.when(taxTypeService.getTaxTypeById(eq(id))).thenReturn(taxType);

        // Act
        ResponseEntity<TaxTypeResponseDTO> response = taxTypeController.getTaxTypeById(id);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(taxType, response.getBody());
    }

    @Test
    void testCreateTaxType() {
        // Arrange
        TaxTypeRequestDTO requestDTO = new TaxTypeRequestDTO();
        requestDTO.setNome("IPI");
        requestDTO.setDescricao("Imposto sobre Produtos Industrializados");
        requestDTO.setAliquota(BigDecimal.valueOf(12.0));

        TaxTypeResponseDTO responseDTO = new TaxTypeResponseDTO(3, "IPI", "Imposto sobre Produtos Industrializados", BigDecimal.valueOf(12.0));
        Mockito.when(taxTypeService.createTaxType(any(TaxTypeRequestDTO.class))).thenReturn(responseDTO);

        // Act
        ResponseEntity<TaxTypeResponseDTO> response = taxTypeController.createTaxType(requestDTO);

        // Assert
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(responseDTO, response.getBody());
    }

    @Test
    void testDeleteTaxType() {
        // Arrange
        Integer id = 1;
        Mockito.doNothing().when(taxTypeService).deleteTaxType(eq(id));

        // Act
        ResponseEntity<Void> response = taxTypeController.deleteTaxType(id);

        // Assert
        assertEquals(204, response.getStatusCodeValue());
    }
}