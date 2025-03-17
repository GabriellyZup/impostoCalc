package com.impostoCalc.service.test;

import com.impostoCalc.dtos.TaxTypeRequestDTO;
import com.impostoCalc.dtos.TaxTypeResponseDTO;
import com.impostoCalc.model.TaxType;
import com.impostoCalc.repository.TaxTypeRepository;
import com.impostoCalc.repository.CustomTaxTypeRepository;
import com.impostoCalc.service.TaxTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaxTypeServiceTest {

    @InjectMocks
    private TaxTypeService taxTypeService;

    @Mock
    private TaxTypeRepository taxTypeRepository;

    @Mock
    private CustomTaxTypeRepository customTaxTypeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTaxTypes() {
        // Arrange
        TaxType taxType1 = new TaxType();
        taxType1.setId(1);
        taxType1.setNome("ICMS");

        TaxType taxType2 = new TaxType();
        taxType2.setId(2);
        taxType2.setNome("ISS");

        when(taxTypeRepository.findAll()).thenReturn(Arrays.asList(taxType1, taxType2));

        // Act
        List<TaxTypeResponseDTO> result = taxTypeService.getAllTaxTypes();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(taxTypeRepository, times(1)).findAll();
    }

    @Test
    void testGetTaxTypeById_Success() {
        // Arrange
        TaxType taxType = new TaxType();
        taxType.setId(1);
        taxType.setNome("ICMS");

        when(taxTypeRepository.findById(1)).thenReturn(Optional.of(taxType));

        // Act
        TaxTypeResponseDTO result = taxTypeService.getTaxTypeById(1);

        // Assert
        assertNotNull(result);
        assertEquals("ICMS", result.getNome());
        verify(taxTypeRepository, times(1)).findById(1);
    }

    @Test
    void testGetTaxTypeById_NotFound() {
        // Arrange
        when(taxTypeRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            taxTypeService.getTaxTypeById(1);
        });

        assertEquals("Tipo de imposto não encontrado.", exception.getMessage());
        verify(taxTypeRepository, times(1)).findById(1);
    }
}
