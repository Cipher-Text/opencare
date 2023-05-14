package com.ciphertext.medicalinformationbackend.service;

import com.ciphertext.medicalinformationbackend.dto.out.DoctorDTO;
import com.ciphertext.medicalinformationbackend.exception.RecordNotFoundException;
import com.ciphertext.medicalinformationbackend.iservice.DoctorService;
import com.ciphertext.medicalinformationbackend.model.Doctor;
import com.ciphertext.medicalinformationbackend.repository.DoctorRepository;
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
    public Doctor getDoctorById(int id) throws RecordNotFoundException {
        return doctorRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }
}
