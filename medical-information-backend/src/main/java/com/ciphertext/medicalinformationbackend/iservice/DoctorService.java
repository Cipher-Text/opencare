package com.ciphertext.medicalinformationbackend.iservice;

import com.ciphertext.medicalinformationbackend.dto.out.DoctorDTO;
import com.ciphertext.medicalinformationbackend.exception.RecordNotFoundException;
import com.ciphertext.medicalinformationbackend.model.Doctor;

import java.util.List;

/**
 * @author Sadman
 */
public interface DoctorService {
    List<DoctorDTO> getAllDoctors();
    Doctor getDoctorById(int id) throws RecordNotFoundException;
}
