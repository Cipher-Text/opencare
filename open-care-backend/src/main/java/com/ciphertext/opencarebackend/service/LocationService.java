package com.ciphertext.opencarebackend.service;

import com.ciphertext.opencarebackend.exception.ResourceNotFoundException;
import com.ciphertext.opencarebackend.model.entity.District;
import com.ciphertext.opencarebackend.model.entity.Division;
import com.ciphertext.opencarebackend.model.entity.Union;
import com.ciphertext.opencarebackend.model.entity.Upazila;

import java.util.List;

/**
 * @author Sadman
 */
public interface LocationService {
    List<Division> getAllDivisions();
    Division getDivisionById(int id) throws ResourceNotFoundException;
    List<District> getAllDistricts();
    District getDistrictById(int id) throws ResourceNotFoundException;
    List<District> getAllDistrictsByDivisionId(int divisionId) throws ResourceNotFoundException;
    List<Upazila> getAllUpazilas();
    Upazila getUpazilaById(int id) throws ResourceNotFoundException;
    List<Upazila> getAllUpazilasByDistrictId(int districtId) throws ResourceNotFoundException;

    List<Union> getAllUnionsByUpazilaId(int upazilaId) throws ResourceNotFoundException;
}
