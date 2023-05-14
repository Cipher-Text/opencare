package com.ciphertext.medicalinformationbackend.service;

import com.ciphertext.medicalinformationbackend.exception.RecordNotFoundException;
import com.ciphertext.medicalinformationbackend.iservice.DoctorWorkplaceService;
import com.ciphertext.medicalinformationbackend.model.DoctorWorkplace;
import com.ciphertext.medicalinformationbackend.repository.DoctorWorkplaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sadman
 */
@Service
public class DoctorWorkplaceServiceImpl implements DoctorWorkplaceService {

    @Autowired
    DoctorWorkplaceRepository doctorWorkplaceRepository;

    @Override
    public List<DoctorWorkplace> getAllDoctorWorkplaces() {
        return doctorWorkplaceRepository.findAll();
    }

    @Override
    public DoctorWorkplace getDoctorWorkplaceById(int id) throws RecordNotFoundException {
        return doctorWorkplaceRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }
}
