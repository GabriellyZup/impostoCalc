package com.impostoCalc.controller.test;

import com.impostoCalc.controller.TaxCalculationController;
import com.impostoCalc.dtos.request.TaxCalculationRequestDTO;
import com.impostoCalc.dtos.response.TaxCalculationResponseDTO;
import com.impostoCalc.service.TaxCalculationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

class TaxCalculationControllerTest {

    private TaxCalculationController taxCalculationController;

    @Mock
    private TaxCalculationService taxCalculationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        taxCalculationController = new TaxCalculationController(taxCalculationService);
    }

    @Test
    void testCalculateTax() {
        // Arrange
        TaxCalculationRequestDTO requestDTO = new TaxCalculationRequestDTO();
        requestDTO.setTipoImpostoId(1);
        requestDTO.setValorBase(BigDecimal.valueOf(1000.0));

        TaxCalculationResponseDTO responseDTO = new TaxCalculationResponseDTO("ICMS", BigDecimal.valueOf(1000.0), BigDecimal.valueOf(18.0), BigDecimal.valueOf(180.0));
        Mockito.when(taxCalculationService.calculateTax(any(TaxCalculationRequestDTO.class))).thenReturn(responseDTO);

        // Act
        ResponseEntity<TaxCalculationResponseDTO> response = taxCalculationController.calculateTax(requestDTO);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(responseDTO, response.getBody());
    }
}