package com.ciphertext.opencarebackend.service;

import com.ciphertext.opencarebackend.model.dto.DoctorDTO;
import com.ciphertext.opencarebackend.exception.ResourceNotFoundException;
import com.ciphertext.opencarebackend.model.entity.Doctor;

import java.util.List;

/**
 * @author Sadman
 */
public interface DoctorService {
    List<DoctorDTO> getAllDoctors();
    Doctor getDoctorById(Long id) throws ResourceNotFoundException;
}
