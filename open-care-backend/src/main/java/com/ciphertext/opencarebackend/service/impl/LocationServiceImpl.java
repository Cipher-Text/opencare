package com.ciphertext.opencarebackend.service.impl;

import com.ciphertext.opencarebackend.exception.ResourceNotFoundException;
import com.ciphertext.opencarebackend.service.LocationService;
import com.ciphertext.opencarebackend.model.entity.District;
import com.ciphertext.opencarebackend.model.entity.Division;
import com.ciphertext.opencarebackend.model.entity.Union;
import com.ciphertext.opencarebackend.model.entity.Upazila;
import com.ciphertext.opencarebackend.repository.DistrictRepository;
import com.ciphertext.opencarebackend.repository.DivisionRepository;
import com.ciphertext.opencarebackend.repository.UnionRepository;
import com.ciphertext.opencarebackend.repository.UpazilaRepository;
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

    @Autowired
    UnionRepository unionRepository;

    @Override
    public List<Division> getAllDivisions() {
        return divisionRepository.findAll();
    }

    @Override
    public Division getDivisionById(int id) throws ResourceNotFoundException {
        return divisionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found Division with id = " + id));
    }

    @Override
    public List<District> getAllDistricts() {
        return districtRepository.findAll();
    }

    @Override
    public District getDistrictById(int id) throws ResourceNotFoundException {
        return districtRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found District with id = " + id));
    }

    @Override
    public List<District> getAllDistrictsByDivisionId(int divisionId) throws ResourceNotFoundException {
        Division division = getDivisionById(divisionId);
        return districtRepository.getAllByDivision(division);
    }

    @Override
    public List<Upazila> getAllUpazilas() {
        return upazilaRepository.findAll();
    }

    @Override
    public Upazila getUpazilaById(int id) throws ResourceNotFoundException {
        return upazilaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found Upazila with id = " + id));
    }

    @Override
    public List<Upazila> getAllUpazilasByDistrictId(int districtId) throws ResourceNotFoundException {
        District district = getDistrictById(districtId);
        return upazilaRepository.getAllByDistrict(district);
    }

    @Override
    public List<Union> getAllUnionsByUpazilaId(int upazilaId) throws ResourceNotFoundException {
        Upazila upazila = getUpazilaById(upazilaId);
        return unionRepository.getAllByUpazila(upazila);
    }
}
