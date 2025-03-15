package com.impostoCalc.service;

import com.impostoCalc.dtos.TaxTypeRequestDTO;
import com.impostoCalc.dtos.TaxTypeResponseDTO;
import com.impostoCalc.model.TaxType;
import com.impostoCalc.repository.TaxTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaxTypeService {

    private final TaxTypeRepository taxTypeRepository;

    @Autowired
    public TaxTypeService(TaxTypeRepository taxTypeRepository) {
        this.taxTypeRepository = taxTypeRepository;
    }

    public List<TaxTypeResponseDTO> getAllTaxTypes() {
        return taxTypeRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public TaxTypeResponseDTO getTaxTypeById(Integer id) {
        TaxType taxType = taxTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de imposto não encontrado."));
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
            throw new RuntimeException("Tipo de imposto não encontrado.");
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