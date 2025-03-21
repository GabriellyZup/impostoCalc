package com.impostoCalc.repository.test;

import com.impostoCalc.model.TaxType;
import com.impostoCalc.repository.CustomTaxTypeRepositoryImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        String nome = "ICMS";
        TaxType taxType = new TaxType();
        taxType.setId(1);
        taxType.setNome(nome);
        taxType.setDescricao("Imposto sobre Circulação de Mercadorias e Serviços");
        taxType.setAliquota(BigDecimal.valueOf(18.0));

        Mockito.when(entityManager.createQuery(Mockito.anyString(), Mockito.eq(TaxType.class))).thenReturn(typedQuery);
        Mockito.when(typedQuery.setParameter(Mockito.eq("nome"), Mockito.eq(nome))).thenReturn(typedQuery);
        Mockito.when(typedQuery.getResultStream()).thenReturn(java.util.stream.Stream.of(taxType));

        // Act
        Optional<TaxType> result = customTaxTypeRepository.findByName(nome);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(nome, result.get().getNome());
    }
}