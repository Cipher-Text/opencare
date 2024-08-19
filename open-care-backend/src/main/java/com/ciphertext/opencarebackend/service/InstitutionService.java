package com.ciphertext.opencarebackend.service;

import com.ciphertext.opencarebackend.model.dto.InstitutionDTO;
import com.ciphertext.opencarebackend.exception.ResourceNotFoundException;
import com.ciphertext.opencarebackend.model.entity.Institution;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Sadman
 */
public interface InstitutionService {
    List<Institution> getAllInstitutions();
    Institution getInstitutionById(int id) throws ResourceNotFoundException;
    Page<InstitutionDTO> getPaginatedDataWithFilters(String name, String bnName, Integer numberOfBed, Integer districtId, Pageable pagingSort);
}
