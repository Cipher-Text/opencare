package com.ciphertext.opencarebackend.service.impl;

import com.ciphertext.opencarebackend.exception.ResourceNotFoundException;
import com.ciphertext.opencarebackend.service.DegreeService;
import com.ciphertext.opencarebackend.model.entity.Degree;
import com.ciphertext.opencarebackend.repository.DegreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sadman
 */
@Service
public class DegreeServiceImpl implements DegreeService {

    @Autowired
    DegreeRepository degreeRepository;

    @Override
    public List<Degree> getAllDegrees() {
        return degreeRepository.findAll();
    }

    @Override
    public Degree getDegreeById(int id) throws ResourceNotFoundException {
        return degreeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found Degree with id = " + id));
    }
}
