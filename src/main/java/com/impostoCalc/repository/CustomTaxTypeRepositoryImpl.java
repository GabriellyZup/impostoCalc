package com.impostoCalc.repository;

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
    public EntityManager entityManager;

    @Override
    public Optional<TaxType> findByName(String nome) {
        String jpql = "SELECT t FROM TaxType t WHERE t.nome = :nome";
        TypedQuery<TaxType> query = entityManager.createQuery(jpql, TaxType.class);
        query.setParameter("nome", nome);

        return query.getResultStream().findFirst();
    }
}
