package com.ciphertext.opencarebackend.service;

import com.ciphertext.opencarebackend.exception.ResourceNotFoundException;
import com.ciphertext.opencarebackend.model.entity.MedicalSpeciality;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author Sadman
 */
public interface MedicalSpecialityService {
    List<MedicalSpeciality> getAllSpecialities();
    MedicalSpeciality getSpecialityById(int id) throws ResourceNotFoundException;
    MedicalSpeciality createSpeciality(MedicalSpeciality medicalSpeciality);
    MedicalSpeciality updateSpeciality(MedicalSpeciality newMedicalSpeciality, int specialityId);
    ResponseEntity<Object> deleteSpecialityById(int specialityId);
}
