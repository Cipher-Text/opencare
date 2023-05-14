package com.ciphertext.medicalinformationbackend.service;

import com.ciphertext.medicalinformationbackend.dto.out.DoctorDTO;
import com.ciphertext.medicalinformationbackend.dto.out.HospitalDTO;
import com.ciphertext.medicalinformationbackend.exception.RecordNotFoundException;
import com.ciphertext.medicalinformationbackend.iservice.HospitalService;
import com.ciphertext.medicalinformationbackend.model.Doctor;
import com.ciphertext.medicalinformationbackend.model.Hospital;
import com.ciphertext.medicalinformationbackend.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sadman
 */
@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    HospitalRepository hospitalRepository;

    @Override
    public List<HospitalDTO> getAllHospitals() {
        List<Hospital> hospitals = hospitalRepository.findAll();
        List<HospitalDTO> hospitalDTOS= new ArrayList<>();
        for(Hospital hospital: hospitals){
            HospitalDTO hospitalDTO = new HospitalDTO();
            hospitalDTO.setId(hospital.getId());
            hospitalDTO.setName(hospital.getName());
            hospitalDTO.setDistrict(hospital.getDistrict().getName());
            hospitalDTO.setType(hospital.getType().getName());
            hospitalDTO.setNumberOfBed(hospital.getNumberOfBed());
            hospitalDTOS.add(hospitalDTO);
        }
        return hospitalDTOS;
    }

    @Override
    public Hospital getHospitalById(int id) throws RecordNotFoundException {
        return hospitalRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    @Override
    public Hospital createHospital(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    @Override
    public Hospital updateHospital(Hospital newHospital, int hospitalId) {
        return hospitalRepository.findById(hospitalId)
                .map(hospital -> {
                    hospital.setName(newHospital.getName());
                    hospital.setNumberOfBed(newHospital.getNumberOfBed());
                    hospital.setDistrict(newHospital.getDistrict());
                    return hospitalRepository.save(hospital);
                })
                .orElseGet(() -> {
                    newHospital.setId(hospitalId);
                    return hospitalRepository.save(newHospital);
                });
    }

    @Override
    public ResponseEntity<Object> deleteHospitalById(int hospitalId) {
        hospitalRepository.deleteById(hospitalId);
        if (hospitalRepository.findById(hospitalId).isPresent()) {
            return ResponseEntity.unprocessableEntity().body("Failed to delete the specified record");
        } else return ResponseEntity.ok().body("Hospital is Deleted Successfully");
    }


}
