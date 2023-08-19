package com.ciphertext.opencarebackend.service.impl;

import com.ciphertext.opencarebackend.exception.ResourceNotFoundException;
import com.ciphertext.opencarebackend.service.DistrictService;
import com.ciphertext.opencarebackend.model.entity.District;
import com.ciphertext.opencarebackend.repository.DistrictRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author Sadman
 */
@Service
@RequiredArgsConstructor
public class DistrictServiceImpl implements DistrictService {
    private final DistrictRepository districtRepository;

    @Override
    public List<District> getAllDistricts() {
        return districtRepository.findAll();
    }

    @Override
    public District getDistrictById(int id) throws ResourceNotFoundException {
        return districtRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found District with id = " + id));
    }
}
