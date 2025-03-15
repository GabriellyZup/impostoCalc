package com.impostoCalc.repository;

import com.impostoCalc.model.TaxType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxTypeRepository extends JpaRepository<TaxType, Integer> {
}