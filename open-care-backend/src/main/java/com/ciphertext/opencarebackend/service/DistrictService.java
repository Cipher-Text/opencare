package com.ciphertext.opencarebackend.service;

import com.ciphertext.opencarebackend.exception.ResourceNotFoundException;
import com.ciphertext.opencarebackend.model.entity.District;

import java.util.List;

/**
 * @author Sadman
 */
public interface DistrictService {
    List<District> getAllDistricts();
    District getDistrictById(int id) throws ResourceNotFoundException;
}
