package com.ciphertext.opencarebackend.service.impl;

import com.ciphertext.opencarebackend.model.dto.InstitutionDTO;
import com.ciphertext.opencarebackend.exception.ResourceNotFoundException;
import com.ciphertext.opencarebackend.service.InstitutionService;
import com.ciphertext.opencarebackend.model.entity.Institution;
import com.ciphertext.opencarebackend.repository.InstitutionRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.ciphertext.opencarebackend.model.mappers.InstitutionMapper;

import java.util.List;

/**
 * @author Sadman
 */
@Service
@RequiredArgsConstructor
public class InstitutionServiceImpl implements InstitutionService {

    private final InstitutionMapper institutionMapper = Mappers.getMapper(InstitutionMapper.class);

    private final InstitutionRepository institutionRepository;

    @Override
    public List<Institution> getAllInstitutions() {
        return institutionRepository.findAll();
    }

    @Override
    public Institution getInstitutionById(int id) throws ResourceNotFoundException {
        return institutionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found Institution with id = " + id));
    }

    @Override
    public Page<InstitutionDTO> getPaginatedDataWithFilters(String name, String bnName, Integer enroll,
                                                            Integer districtId, Pageable pagingSort) {
        return institutionRepository.getFilteredInstitutions(name, bnName, enroll, districtId, pagingSort)
                .map(institutionMapper::institutionToInstitutionDTO);
    }

}
