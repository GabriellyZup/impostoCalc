package com.impostoCalc.repository;

import com.impostoCalc.model.TaxType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaxTypeRepositoryImpl {

    @PersistenceContext
    public EntityManager entityManager;

    public List<TaxType> findByCustomQuery(String query) {
        return entityManager.createQuery(query, TaxType.class).getResultList();
    }
}