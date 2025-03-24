package com.impostoCalc.repository.test;

import com.impostoCalc.model.TaxType;
import com.impostoCalc.repository.CustomTaxTypeRepositoryImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomTaxTypeRepositoryImplTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private TypedQuery<TaxType> typedQuery;

    private CustomTaxTypeRepositoryImpl taxTypeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        taxTypeRepository = new CustomTaxTypeRepositoryImpl();
        taxTypeRepository.setEntityManager(entityManager);
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
        verify(entityManager).createQuery(query, TaxType.class); // Ver se o método foi chamado
    }

}