package com.impostoCalc.repository;

import com.impostoCalc.model.TaxType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import lombok.*;
import java.util.List;
import java.util.Optional;
@Setter

public class CustomTaxTypeRepositoryImpl implements CustomTaxTypeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<TaxType> findByNome(String nome) {
        String jpql = "SELECT t FROM TaxType t WHERE t.nome = :nome";
        TypedQuery<TaxType> query = entityManager.createQuery(jpql, TaxType.class);
        query.setParameter("nome", nome);
        return query.getResultStream().findFirst();
    }

    public List<TaxType> findByCustomQuery(String query) {
        return entityManager.createQuery(query, TaxType.class).getResultList();
    }
}