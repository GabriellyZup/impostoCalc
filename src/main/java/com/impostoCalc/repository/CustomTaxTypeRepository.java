package com.impostoCalc.repository;

import com.impostoCalc.model.TaxType;

import java.util.Optional;

public interface CustomTaxTypeRepository {
    Optional<TaxType> findByNome(String nome);
}