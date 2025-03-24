package com.impostoCalc.repository.test;

import model.TaxType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.CustomTaxTypeRepositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomTaxTypeRepositoryImplTest {

    private CustomTaxTypeRepositoryImpl customTaxTypeRepository;

    @Mock
    private EntityManager entityManager;

    @Mock
    private TypedQuery<TaxType> typedQuery;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customTaxTypeRepository = new CustomTaxTypeRepositoryImpl();
        customTaxTypeRepository.entityManager = entityManager;
    }

    @Test
    void testFindByName() {
        // Arrange
        String name = "ICMS";
        TaxType taxType = new TaxType();
        taxType.setId(1);
        taxType.setName(name);
        taxType.setDescription("Imposto sobre Circulação de Mercadorias e Serviços");
        taxType.setRate(BigDecimal.valueOf(18.0));

        when(entityManager.createQuery(anyString(), eq(TaxType.class))).thenReturn(typedQuery);
        when(typedQuery.setParameter(eq("name"), eq(name))).thenReturn(typedQuery);
        when(typedQuery.getResultStream()).thenReturn(java.util.stream.Stream.of(taxType));

        // Act
        Optional<TaxType> result = customTaxTypeRepository.findByName(name);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(name, result.get().getName());
    }
}