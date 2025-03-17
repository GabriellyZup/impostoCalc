package com.impostoCalc.repository.test;

import com.impostoCalc.model.TaxType;
import com.impostoCalc.repository.TaxTypeRepositoryImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaxTypeRepositoryImplTest {

    @InjectMocks
    private TaxTypeRepositoryImpl taxTypeRepository;

    @Mock
    private EntityManager entityManager;

    @Mock
    private TypedQuery<TaxType> typedQuery;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByCustomQuery_Success() {
        // Arrange
        String query = "SELECT t FROM TaxType t";
        TaxType taxType1 = new TaxType();
        taxType1.setNome("ICMS");
        TaxType taxType2 = new TaxType();
        taxType2.setNome("ISS");

        when(entityManager.createQuery(query, TaxType.class)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(List.of(taxType1, taxType2));

        // Act
        List<TaxType> result = taxTypeRepository.findByCustomQuery(query);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(entityManager, times(1)).createQuery(query, TaxType.class);
    }

    @Test
    void testFindByCustomQuery_EmptyResult() {
        // Arrange
        String query = "SELECT t FROM TaxType t";

        when(entityManager.createQuery(query, TaxType.class)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(List.of());

        // Act
        List<TaxType> result = taxTypeRepository.findByCustomQuery(query);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(entityManager, times(1)).createQuery(query, TaxType.class);
    }
}
