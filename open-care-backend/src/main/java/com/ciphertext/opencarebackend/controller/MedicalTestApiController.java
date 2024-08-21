package com.ciphertext.opencarebackend.controller;

import com.ciphertext.opencarebackend.exception.ResourceNotFoundException;
import com.ciphertext.opencarebackend.model.entity.MedicalTest;
import com.ciphertext.opencarebackend.service.MedicalTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Sadman
 */
@RestController
@RequestMapping("/api/medical-tests")
@RequiredArgsConstructor
public class MedicalTestApiController {
    private final MedicalTestService service;

    @GetMapping("")
    public List<MedicalTest> getAllTests() {
        return service.getAllMedicalTests();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalTest> getSpecialityById(@PathVariable(value = "id") int medicalTestId)
            throws ResourceNotFoundException {
        MedicalTest medicalSpeciality = service.getMedicalTestById(medicalTestId);
        return ResponseEntity.ok().body(medicalSpeciality);
    }
}
