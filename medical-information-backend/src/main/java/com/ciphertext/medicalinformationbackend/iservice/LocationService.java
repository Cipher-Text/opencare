package com.ciphertext.medicalinformationbackend.iservice;

import com.ciphertext.medicalinformationbackend.exception.RecordNotFoundException;
import com.ciphertext.medicalinformationbackend.model.District;
import com.ciphertext.medicalinformationbackend.model.Division;
import com.ciphertext.medicalinformationbackend.model.Upazila;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author Sadman
 */
public interface LocationService {
    List<Division> getAllDivisions();
    Division getDivisionById(int id) throws RecordNotFoundException;
    List<District> getAllDistricts();
    District getDistrictById(int id) throws RecordNotFoundException;
    List<District> getAllDistrictsByDivisionId(int divisionId) throws RecordNotFoundException;
    List<Upazila> getAllUpazilas();
    Upazila getUpazilaById(int id) throws RecordNotFoundException;
    List<Upazila> getAllUpazilasByDistrictId(int districtId) throws RecordNotFoundException;
}
