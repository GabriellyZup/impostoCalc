package com.impostoCalc.controller.test;

import com.impostoCalc.controller.TaxCalculationController;
import com.impostoCalc.dtos.TaxCalculationRequestDTO;
import com.impostoCalc.dtos.TaxCalculationResponseDTO;
import com.impostoCalc.service.TaxCalculationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TaxCalculationControllerTest {

    @InjectMocks
    private TaxCalculationController taxCalculationController;

    @Mock
    private TaxCalculationService taxCalculationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculateTax() {
        // Arrange
        TaxCalculationRequestDTO request = new TaxCalculationRequestDTO();
        request.setTipoImpostoId(1);
        request.setValorBase(BigDecimal.valueOf(1000.0));

        TaxCalculationResponseDTO response = new TaxCalculationResponseDTO();
        response.setTipoImposto("ICMS");
        response.setValorBase(BigDecimal.valueOf(1000.0));
        response.setAliquota(BigDecimal.valueOf(18.0));
        response.setValorImposto(BigDecimal.valueOf(180.0));

        when(taxCalculationService.calculateTax(request)).thenReturn(response);

        // Act
        ResponseEntity<TaxCalculationResponseDTO> result = taxCalculationController.calculateTax(request);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(taxCalculationService, times(1)).calculateTax(request);
    }
}