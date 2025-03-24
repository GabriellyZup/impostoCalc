package com.impostoCalc.repository.test;

import com.impostoCalc.model.TaxType;
import com.impostoCalc.repository.CustomTaxTypeRepository;
import com.impostoCalc.repository.TaxTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomTaxTypeRepositoryTest {

    @Mock
    private TaxTypeRepository taxTypeRepository; // Mock do repositório

    @Mock
    private EntityManager entityManager;

    private CustomTaxTypeRepository customTaxTypeRepository; // Assume que TaxTypeRepository implementa CustomTaxTypeRepository

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customTaxTypeRepository = taxTypeRepository; // TaxTypeRepository já inclui CustomTaxTypeRepository
    }

    @Test
    void testFindByNome() {
        // Arrange
        String nome = "ICMS";
        TaxType taxType = new TaxType();
        taxType.setNome(nome);

        // Define o comportamento do mock
        when(taxTypeRepository.findByNome(nome)).thenReturn(Optional.of(taxType));

        // Act
        Optional<TaxType> result = customTaxTypeRepository.findByNome(nome);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(nome, result.get().getNome());
    }
}