package com.ciphertext.medicalinformationbackend.service;

import com.ciphertext.medicalinformationbackend.exception.RecordNotFoundException;
import com.ciphertext.medicalinformationbackend.iservice.SpecialityService;
import com.ciphertext.medicalinformationbackend.model.Speciality;
import com.ciphertext.medicalinformationbackend.repository.SpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sadman
 */
@Service
public class SpecialityServiceImpl implements SpecialityService {

    @Autowired
    SpecialityRepository specialityRepository;

    @Override
    public List<Speciality> getAllSpecialities() {
        return specialityRepository.findAll();
    }

    @Override
    public Speciality getSpecialityById(int id) throws RecordNotFoundException {
        return specialityRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    @Override
    public Speciality createSpeciality(Speciality speciality) {
        return specialityRepository.save(speciality);
    }

    @Override
    public Speciality updateSpeciality(Speciality newSpeciality, int specialityId) {
        return specialityRepository.findById(specialityId)
                .map(speciality -> {
                    speciality.setName(newSpeciality.getName());
                    speciality.setDescription(newSpeciality.getDescription());
                    return specialityRepository.save(speciality);
                })
                .orElseGet(() -> {
                    newSpeciality.setId(specialityId);
                    return specialityRepository.save(newSpeciality);
                });
    }

    @Override
    public ResponseEntity<Object> deleteSpecialityById(int specialityId) {
        specialityRepository.deleteById(specialityId);
        if (specialityRepository.findById(specialityId).isPresent()) {
            return ResponseEntity.unprocessableEntity().body("Failed to delete the specified record");
        } else return ResponseEntity.ok().body("Speciality is Deleted Successfully");
    }
}
