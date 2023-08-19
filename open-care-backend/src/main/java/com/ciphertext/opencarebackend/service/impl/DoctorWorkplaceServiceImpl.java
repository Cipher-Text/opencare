package com.ciphertext.opencarebackend.service.impl;

import com.ciphertext.opencarebackend.exception.ResourceNotFoundException;
import com.ciphertext.opencarebackend.service.DoctorWorkplaceService;
import com.ciphertext.opencarebackend.model.entity.DoctorWorkplace;
import com.ciphertext.opencarebackend.repository.DoctorWorkplaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sadman
 */
@Service
public class DoctorWorkplaceServiceImpl implements DoctorWorkplaceService {

    @Autowired
    DoctorWorkplaceRepository doctorWorkplaceRepository;

    @Override
    public List<DoctorWorkplace> getAllDoctorWorkplaces() {
        return doctorWorkplaceRepository.findAll();
    }

    @Override
    public DoctorWorkplace getDoctorWorkplaceById(int id) throws ResourceNotFoundException {
        return doctorWorkplaceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found DoctorWorkplace with id = " + id));
    }
}
