package com.impostoCalc.repository.test;

import com.impostoCalc.model.TaxType;
import com.impostoCalc.repository.TaxTypeRepositoryImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaxTypeRepositoryImplTest {

    private TaxTypeRepositoryImpl taxTypeRepository;

    @Mock
    private EntityManager entityManager;

    @Mock
    private TypedQuery<TaxType> typedQuery;

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
        TaxType taxType1 = new TaxType();
        taxType1.setId(1);
        taxType1.setNome("ICMS");
        taxType1.setDescricao("Imposto sobre Circulação de Mercadorias e Serviços");
        taxType1.setAliquota(BigDecimal.valueOf(18.0));

        TaxType taxType2 = new TaxType();
        taxType2.setId(2);
        taxType2.setNome("ISS");
        taxType2.setDescricao("Imposto sobre Serviços");
        taxType2.setAliquota(BigDecimal.valueOf(5.0));

        Mockito.when(entityManager.createQuery(query, TaxType.class)).thenReturn(typedQuery);
        Mockito.when(typedQuery.getResultList()).thenReturn(List.of(taxType1, taxType2));

        // Act
        List<TaxType> result = taxTypeRepository.findByCustomQuery(query);

        // Assert
        assertEquals(2, result.size());
        assertEquals("ICMS", result.get(0).getNome());
        assertEquals("ISS", result.get(1).getNome());
    }
}