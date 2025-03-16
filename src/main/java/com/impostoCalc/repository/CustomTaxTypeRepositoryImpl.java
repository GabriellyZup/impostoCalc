package com.impostoCalc.repository.impl;

import com.impostoCalc.model.TaxType;
import com.impostoCalc.repository.CustomTaxTypeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CustomTaxTypeRepositoryImpl implements CustomTaxTypeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<TaxType> findByName(String name) {
        String jpql = "SELECT t FROM TaxType t WHERE t.nome = :name";
        TypedQuery<TaxType> query = entityManager.createQuery(jpql, TaxType.class);
        query.setParameter("name", name);

        return query.getResultStream().findFirst();
    }
}
