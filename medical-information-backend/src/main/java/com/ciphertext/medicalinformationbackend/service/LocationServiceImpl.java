package com.ciphertext.medicalinformationbackend.service;

import com.ciphertext.medicalinformationbackend.exception.RecordNotFoundException;
import com.ciphertext.medicalinformationbackend.iservice.LocationService;
import com.ciphertext.medicalinformationbackend.model.District;
import com.ciphertext.medicalinformationbackend.model.Division;
import com.ciphertext.medicalinformationbackend.model.Upazila;
import com.ciphertext.medicalinformationbackend.repository.DistrictRepository;
import com.ciphertext.medicalinformationbackend.repository.DivisionRepository;
import com.ciphertext.medicalinformationbackend.repository.UpazilaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sadman
 */
@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    DivisionRepository divisionRepository;

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    UpazilaRepository upazilaRepository;

    @Override
    public List<Division> getAllDivisions() {
        return divisionRepository.findAll();
    }

    @Override
    public Division getDivisionById(int id) throws RecordNotFoundException {
        return divisionRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    @Override
    public List<District> getAllDistricts() {
        return districtRepository.findAll();
    }

    @Override
    public District getDistrictById(int id) throws RecordNotFoundException {
        return districtRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    @Override
    public List<District> getAllDistrictsByDivisionId(int divisionId) throws RecordNotFoundException {
        Division division = getDivisionById(divisionId);
        return districtRepository.getAllByDivision(division);
    }

    @Override
    public List<Upazila> getAllUpazilas() {
        return upazilaRepository.findAll();
    }

    @Override
    public Upazila getUpazilaById(int id) throws RecordNotFoundException {
        return upazilaRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    @Override
    public List<Upazila> getAllUpazilasByDistrictId(int districtId) throws RecordNotFoundException {
        District district = getDistrictById(districtId);
        return upazilaRepository.getAllByDistrict(district);
    }
}
