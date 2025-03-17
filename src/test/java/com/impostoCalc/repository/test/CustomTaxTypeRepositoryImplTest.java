package com.impostoCalc.repository.test;

import com.impostoCalc.model.TaxType;
import com.impostoCalc.repository.CustomTaxTypeRepositoryImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomTaxTypeRepositoryImplTest {

    @InjectMocks
    private CustomTaxTypeRepositoryImpl customTaxTypeRepository;

    @Mock
    private EntityManager entityManager;

    @Mock
    private TypedQuery<TaxType> typedQuery;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByName_Success() {
        // Arrange
        String nome = "ICMS";
        TaxType taxType = new TaxType();
        taxType.setNome(nome);

        when(entityManager.createQuery(anyString(), eq(TaxType.class))).thenReturn(typedQuery);
        when(typedQuery.setParameter("nome", nome)).thenReturn(typedQuery);
        when(typedQuery.getResultStream()).thenReturn(java.util.stream.Stream.of(taxType));

        // Act
        Optional<TaxType> result = customTaxTypeRepository.findByName(nome);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(nome, result.get().getNome());
        verify(entityManager, times(1)).createQuery(anyString(), eq(TaxType.class));
        verify(typedQuery, times(1)).setParameter("nome", nome);
    }

    @Test
    void testFindByName_NotFound() {
        // Arrange
        String nome = "ICMS";

        when(entityManager.createQuery(anyString(), eq(TaxType.class))).thenReturn(typedQuery);
        when(typedQuery.setParameter("nome", nome)).thenReturn(typedQuery);
        when(typedQuery.getResultStream()).thenReturn(java.util.stream.Stream.empty());

        // Act
        Optional<TaxType> result = customTaxTypeRepository.findByName(nome);

        // Assert
        assertFalse(result.isPresent());
        verify(entityManager, times(1)).createQuery(anyString(), eq(TaxType.class));
        verify(typedQuery, times(1)).setParameter("nome", nome);
    }
}