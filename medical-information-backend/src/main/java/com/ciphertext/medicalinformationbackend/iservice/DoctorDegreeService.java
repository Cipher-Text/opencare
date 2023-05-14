package com.ciphertext.medicalinformationbackend.iservice;

import com.ciphertext.medicalinformationbackend.exception.RecordNotFoundException;
import com.ciphertext.medicalinformationbackend.model.DoctorDegree;

import java.util.List;

/**
 * @author Sadman
 */
public interface DoctorDegreeService {
    List<DoctorDegree> getAllDoctorDegrees();
    DoctorDegree getDoctorDegreeById(int id) throws RecordNotFoundException;
}
