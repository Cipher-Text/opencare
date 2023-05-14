package com.ciphertext.medicalinformationbackend.iservice;

import com.ciphertext.medicalinformationbackend.dto.out.HospitalDTO;
import com.ciphertext.medicalinformationbackend.exception.RecordNotFoundException;
import com.ciphertext.medicalinformationbackend.model.Hospital;
import com.ciphertext.medicalinformationbackend.model.Institution;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author Sadman
 */
public interface HospitalService {
    List<HospitalDTO> getAllHospitals();
    Hospital getHospitalById(int id) throws RecordNotFoundException;
    Hospital createHospital(Hospital hospital);
    Hospital updateHospital(Hospital newHospital, int hospitalId);
    ResponseEntity<Object> deleteHospitalById(int hospitalId);
}
