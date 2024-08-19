package com.ciphertext.opencarebackend.service;

import com.ciphertext.opencarebackend.exception.ResourceNotFoundException;
import com.ciphertext.opencarebackend.model.entity.MedicalTest;

import java.util.List;

public interface MedicalTestService {
    List<MedicalTest> getAllMedicalTests();
    MedicalTest getMedicalTestById(int id) throws ResourceNotFoundException;
}
