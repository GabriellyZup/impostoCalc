package com.impostoCalc.service;

import com.impostoCalc.dtos.request.TaxTypeRequestDTO;
import com.impostoCalc.dtos.response.TaxTypeResponseDTO;
import com.impostoCalc.model.TaxType;
import com.impostoCalc.repository.CustomTaxTypeRepository;
import com.impostoCalc.repository.TaxTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaxTypeService {

    private final TaxTypeRepository taxTypeRepository;

    public List<TaxTypeResponseDTO> getAllTaxTypes() {
        return taxTypeRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public TaxTypeResponseDTO getTaxTypeById(Integer id) {
        TaxType taxType = taxTypeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de imposto não encontrado."));
        return toResponseDTO(taxType);
    }

    public TaxTypeResponseDTO createTaxType(TaxTypeRequestDTO requestDTO) {
        TaxType taxType = new TaxType();
        taxType.setNome(requestDTO.getNome());
        taxType.setDescricao(requestDTO.getDescricao());
        taxType.setAliquota(requestDTO.getAliquota());
        TaxType savedTaxType = taxTypeRepository.save(taxType);
        return toResponseDTO(savedTaxType);
    }

    public void deleteTaxType(Integer id) {
        if (!taxTypeRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de imposto não encontrado.");
        }
        taxTypeRepository.deleteById(id);
    }

    private TaxTypeResponseDTO toResponseDTO(TaxType taxType) {
        TaxTypeResponseDTO responseDTO = new TaxTypeResponseDTO();
        responseDTO.setId(taxType.getId());
        responseDTO.setNome(taxType.getNome());
        responseDTO.setDescricao(taxType.getDescricao());
        responseDTO.setAliquota(taxType.getAliquota());
        return responseDTO;
    }
}