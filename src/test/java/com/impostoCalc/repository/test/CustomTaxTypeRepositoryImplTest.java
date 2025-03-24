package com.impostoCalc.repository.test;

import com.impostoCalc.model.TaxType;
import com.impostoCalc.repository.TaxTypeRepositoryImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaxTypeRepositoryImplTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private TypedQuery<TaxType> typedQuery;

    private TaxTypeRepositoryImpl taxTypeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        taxTypeRepository = new TaxTypeRepositoryImpl();
        taxTypeRepository.entityManager = entityManager;
    }

    @Test
    void testFindByCustomQuery() {
        // Arrange
        String query = "SELECT t FROM TaxType t";
        TaxType taxType = new TaxType(1, "ICMS", "Descrição", null);
        when(entityManager.createQuery(query, TaxType.class)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(List.of(taxType));

        // Act
        List<TaxType> result = taxTypeRepository.findByCustomQuery(query);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("ICMS", result.get(0).getNome());
    }
}