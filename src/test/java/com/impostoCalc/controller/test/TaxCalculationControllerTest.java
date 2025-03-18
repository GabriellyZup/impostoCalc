package com.impostoCalc.controller.test;

import com.impostoCalc.controller.TaxCalculationController;
import com.impostoCalc.dtos.TaxCalculationRequestDTO;
import com.impostoCalc.dtos.TaxCalculationResponseDTO;
import com.impostoCalc.service.TaxCalculationService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TaxCalculationControllerTest {

    @InjectMocks
    private TaxCalculationController taxCalculationController;

    @Mock
    private TaxCalculationService taxCalculationService;

    public TaxCalculationControllerTest() {
        MockitoAnnotations.openMocks(this);
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
}