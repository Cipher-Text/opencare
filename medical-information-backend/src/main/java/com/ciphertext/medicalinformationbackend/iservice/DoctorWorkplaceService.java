package com.ciphertext.medicalinformationbackend.iservice;

import com.ciphertext.medicalinformationbackend.exception.RecordNotFoundException;
import com.ciphertext.medicalinformationbackend.model.DoctorWorkplace;

import java.util.List;

/**
 * @author Sadman
 */
public interface DoctorWorkplaceService {
    List<DoctorWorkplace> getAllDoctorWorkplaces();
    DoctorWorkplace getDoctorWorkplaceById(int id) throws RecordNotFoundException;
}
