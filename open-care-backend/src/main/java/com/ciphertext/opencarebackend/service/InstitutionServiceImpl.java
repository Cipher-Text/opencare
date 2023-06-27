package com.ciphertext.opencarebackend.service;

import com.ciphertext.opencarebackend.exception.ResourceNotFoundException;
import com.ciphertext.opencarebackend.iservice.InstitutionService;
import com.ciphertext.opencarebackend.model.Institution;
import com.ciphertext.opencarebackend.repository.InstitutionRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ciphertext.opencarebackend.mappers.InstitutionMapper;

import java.util.List;

/**
 * @author Sadman
 */
@Service
public class InstitutionServiceImpl implements InstitutionService {

    InstitutionMapper mapper = Mappers.getMapper(InstitutionMapper.class);

    @Autowired
    InstitutionRepository institutionRepository;

    @Override
    public List<Institution> getAllInstitutions() {
        return institutionRepository.findAll();
    }

    @Override
    public Institution getInstitutionById(int id) throws ResourceNotFoundException {
        return institutionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found Institution with id = " + id));
    }
}
