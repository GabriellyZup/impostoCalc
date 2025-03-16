package com.impostoCalc.service;

import com.impostoCalc.dtos.TaxCalculationRequestDTO;
import com.impostoCalc.dtos.TaxCalculationResponseDTO;
import com.impostoCalc.model.TaxType;
import com.impostoCalc.repository.TaxTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TaxCalculationService {

    @Autowired
    private TaxTypeRepository taxTypeRepository;

    public TaxCalculationResponseDTO calculateTax(TaxCalculationRequestDTO request) {
        Optional<TaxType> taxTypeOptional = taxTypeRepository.findById(request.getTipoImpostoId());

        if (taxTypeOptional.isEmpty()) {
            throw new IllegalArgumentException("Tipo de imposto não encontrado para o ID fornecido.");
        }

        TaxType taxType = taxTypeOptional.get();

        BigDecimal taxValue = request.getValorBase()
                .multiply(taxType.getAliquota())
                .divide(BigDecimal.valueOf(100));

        TaxCalculationResponseDTO response = new TaxCalculationResponseDTO();
        response.setTipoImposto(taxType.getNome());
        response.setValorBase(request.getValorBase());
        response.setAliquota(taxType.getAliquota());
        response.setValorImposto(taxValue);

        return response;
    }
}