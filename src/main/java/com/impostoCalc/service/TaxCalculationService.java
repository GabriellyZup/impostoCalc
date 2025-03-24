package com.impostoCalc.service;

import com.impostoCalc.dtos.request.TaxCalculationRequestDTO;
import com.impostoCalc.dtos.response.TaxCalculationResponseDTO;
import com.impostoCalc.model.TaxType;
import com.impostoCalc.repository.TaxTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TaxCalculationService {

    private final TaxTypeRepository taxTypeRepository;

    public TaxCalculationResponseDTO calculateTax(TaxCalculationRequestDTO request) {
        // Busca o tipo de imposto pelo ID
        TaxType taxType = taxTypeRepository.findById(request.getTipoImpostoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de imposto não encontrado"));

        // Valida se a alíquota é válida
        if (taxType.getAliquota().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Alíquota inválida");
        }

        // Calcula o valor do imposto
        BigDecimal valorImposto = request.getValorBase()
                .multiply(taxType.getAliquota())
                .divide(BigDecimal.valueOf(100));

        // Cria a resposta
        TaxCalculationResponseDTO response = new TaxCalculationResponseDTO();
        response.setTipoImposto(taxType.getNome());
        response.setValorBase(request.getValorBase());
        response.setAliquota(taxType.getAliquota());
        response.setValorImposto(valorImposto);

        return response;
    }
}