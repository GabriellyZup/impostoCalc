package com.impostoCalc.service;

import com.impostoCalc.dtos.TaxCalculationRequestDTO;
import com.impostoCalc.dtos.TaxCalculationResponseDTO;
import com.impostoCalc.model.TaxType;
import com.impostoCalc.repository.TaxTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TaxCalculationService {

    @Autowired
    private TaxTypeRepository taxTypeRepository;

    public TaxCalculationResponseDTO calculateTax(TaxCalculationRequestDTO request) {
        TaxType taxType = taxTypeRepository.findById(request.getTipoImpostoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de imposto não encontrado"));

        if (taxType.getAliquota().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Alíquota inválida");
        }

        BigDecimal valorImposto = request.getValorBase()
                .multiply(taxType.getAliquota())
                .divide(BigDecimal.valueOf(100));

        TaxCalculationResponseDTO response = new TaxCalculationResponseDTO();
        response.setTipoImposto(taxType.getNome());
        response.setValorBase(request.getValorBase());
        response.setAliquota(taxType.getAliquota());
        response.setValorImposto(valorImposto);

        return response;
    }
}