package com.impostoCalc.service.test;

import com.impostoCalc.dtos.TaxTypeRequestDTO;
import com.impostoCalc.dtos.TaxTypeResponseDTO;
import com.impostoCalc.model.TaxType;
import com.impostoCalc.repository.CustomTaxTypeRepository;
import com.impostoCalc.repository.TaxTypeRepository;
import com.impostoCalc.service.TaxTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TaxTypeServiceTest {

    private TaxTypeService taxTypeService;

    @Mock
    private TaxTypeRepository taxTypeRepository;

    @Mock
    private CustomTaxTypeRepository customTaxTypeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        taxTypeService = new TaxTypeService(taxTypeRepository, customTaxTypeRepository);
    }

    @Test
    void testGetAllTaxTypes() {
        // Arrange
        TaxType taxType1 = new TaxType();
        taxType1.setId(1);
        taxType1.setNome("ICMS");
        taxType1.setAliquota(BigDecimal.valueOf(18));

        TaxType taxType2 = new TaxType();
        taxType2.setId(2);
        taxType2.setNome("ISS");
        taxType2.setAliquota(BigDecimal.valueOf(5));

        Mockito.when(taxTypeRepository.findAll()).thenReturn(List.of(taxType1, taxType2));

        // Act
        List<TaxTypeResponseDTO> result = taxTypeService.getAllTaxTypes();

        // Assert
        assertEquals(2, result.size());
        assertEquals("ICMS", result.get(0).getNome());
        assertEquals("ISS", result.get(1).getNome());
    }

    @Test
    void testGetTaxTypeById_Success() {
        // Arrange
        TaxType taxType = new TaxType();
        taxType.setId(1);
        taxType.setNome("ICMS");

        Mockito.when(taxTypeRepository.findById(1)).thenReturn(Optional.of(taxType));

        // Act
        TaxTypeResponseDTO result = taxTypeService.getTaxTypeById(1);

        // Assert
        assertNotNull(result);
        assertEquals("ICMS", result.getNome());
    }

    @Test
    void testGetTaxTypeById_NotFound() {
        // Arrange
        Mockito.when(taxTypeRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> taxTypeService.getTaxTypeById(1));
        assertEquals("Tipo de imposto não encontrado.", exception.getMessage());
    }

    @Test
    void testCreateTaxType() {
        // Arrange
        TaxTypeRequestDTO requestDTO = new TaxTypeRequestDTO();
        requestDTO.setNome("ICMS");
        requestDTO.setDescricao("Imposto sobre Circulação de Mercadorias e Serviços");
        requestDTO.setAliquota(BigDecimal.valueOf(18));

        TaxType taxType = new TaxType();
        taxType.setId(1);
        taxType.setNome("ICMS");

        Mockito.when(taxTypeRepository.save(Mockito.any(TaxType.class))).thenReturn(taxType);

        // Act
        TaxTypeResponseDTO result = taxTypeService.createTaxType(requestDTO);

        // Assert
        assertNotNull(result);
        assertEquals("ICMS", result.getNome());
    }
}