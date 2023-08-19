package com.ciphertext.opencarebackend.service.impl;

import com.ciphertext.opencarebackend.exception.ResourceNotFoundException;
import com.ciphertext.opencarebackend.service.DistrictService;
import com.ciphertext.opencarebackend.model.entity.District;
import com.ciphertext.opencarebackend.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author Sadman
 */
@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    DistrictRepository DistrictRepository;

    @Override
    public List<District> getAllDistricts() {
        return DistrictRepository.findAll();
    }

    @Override
    public District getDistrictById(int id) throws ResourceNotFoundException {
        return DistrictRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found District with id = " + id));
    }
}
