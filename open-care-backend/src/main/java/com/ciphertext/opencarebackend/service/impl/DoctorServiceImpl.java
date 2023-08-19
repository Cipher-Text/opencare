package com.ciphertext.opencarebackend.service.impl;

import com.ciphertext.opencarebackend.model.dto.DoctorDTO;
import com.ciphertext.opencarebackend.exception.ResourceNotFoundException;
import com.ciphertext.opencarebackend.service.DoctorService;
import com.ciphertext.opencarebackend.model.entity.Doctor;
import com.ciphertext.opencarebackend.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sadman
 */
@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public List<DoctorDTO> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        List<DoctorDTO> doctorDTOS= new ArrayList<>();
        for(Doctor doctor: doctors){
            DoctorDTO doctorDTO = new DoctorDTO();
            doctorDTO.setId(doctor.getId());
            doctorDTO.setName(doctor.getName());
            doctorDTO.setDegrees("MBBS FCPS");
            doctorDTO.setSpecialities("Anesthesiology");
            doctorDTO.setYearOfExperience(15);
            doctorDTO.setDescription(doctor.getDescription());
            doctorDTOS.add(doctorDTO);
        }
        return doctorDTOS;
    }

    @Override
    public Doctor getDoctorById(Long id) throws ResourceNotFoundException {
        return doctorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found Doctor with id = " + id));
    }
}
