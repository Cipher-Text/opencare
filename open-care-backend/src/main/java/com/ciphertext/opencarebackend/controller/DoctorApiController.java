package com.ciphertext.opencarebackend.controller;

import com.ciphertext.opencarebackend.annotations.SecureAPI;
import com.ciphertext.opencarebackend.model.dto.DoctorDTO;
import com.ciphertext.opencarebackend.exception.ResourceNotFoundException;
import com.ciphertext.opencarebackend.service.DoctorService;
import com.ciphertext.opencarebackend.model.entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Sadman
 */
@RestController
@RequestMapping("/api/doctors")
@SecureAPI
public class DoctorApiController {

    @Autowired
    DoctorService service;

    @GetMapping("")
    public List<DoctorDTO> getAllDoctors(Model model) {
        return service.getAllDoctors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable(value = "id") Long doctorId)
            throws ResourceNotFoundException {
        Doctor doctor = service.getDoctorById(doctorId);
        return ResponseEntity.ok().body(doctor);
    }
}
