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
import org.springframework.security.test.context.support.WithMockUser;

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
    @WithMockUser(roles = "USER")
    void testCalculateTax_AsUser() {
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
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(response, result.getBody());
        verify(taxCalculationService, times(1)).calculateTax(request);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testCalculateTax_AsAdmin() {
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
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(response, result.getBody());
        verify(taxCalculationService, times(1)).calculateTax(request);
    }

    @Test
    void testCalculateTax_WithoutAuthentication() {
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