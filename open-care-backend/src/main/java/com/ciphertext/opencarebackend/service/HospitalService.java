package com.ciphertext.opencarebackend.service;

import com.ciphertext.opencarebackend.model.dto.HospitalDTO;
import com.ciphertext.opencarebackend.exception.ResourceNotFoundException;
import com.ciphertext.opencarebackend.model.entity.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author Sadman
 */
public interface HospitalService {
    List<HospitalDTO> getAllHospitals();
    Page<HospitalDTO> getPaginatedDataWithFilters(String name, String bnName, Integer numberOfBed, Integer districtId, Pageable pagingSort);
    Hospital getHospitalById(int id) throws ResourceNotFoundException;
    Hospital createHospital(Hospital hospital);
    Hospital updateHospital(Hospital newHospital, int hospitalId);
    ResponseEntity<Object> deleteHospitalById(int hospitalId);
}
