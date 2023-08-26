package com.ciphertext.opencarebackend.service.impl;

import com.ciphertext.opencarebackend.exception.ResourceNotFoundException;
import com.ciphertext.opencarebackend.model.entity.MedicalTest;
import com.ciphertext.opencarebackend.repository.MedicalTestRepository;
import com.ciphertext.opencarebackend.service.MedicalTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalTestServiceImpl implements MedicalTestService {

    @Autowired
    MedicalTestRepository medicalTestRepository;

    @Override
    public List<MedicalTest> getAllMedicalTests() {
        return medicalTestRepository.findAll();
    }

    @Override
    public MedicalTest getMedicalTestById(int id) throws ResourceNotFoundException {
        return medicalTestRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found Medical Test with id = " + id));
    }
}
