package com.ciphertext.opencarebackend.service;

import com.ciphertext.opencarebackend.exception.ResourceNotFoundException;
import com.ciphertext.opencarebackend.model.entity.DoctorDegree;

import java.util.List;

/**
 * @author Sadman
 */
public interface DoctorDegreeService {
    List<DoctorDegree> getAllDoctorDegrees();
    DoctorDegree getDoctorDegreeById(int id) throws ResourceNotFoundException;
}
