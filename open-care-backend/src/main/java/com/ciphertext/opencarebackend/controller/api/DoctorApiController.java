package com.ciphertext.opencarebackend.controller.api;

import com.ciphertext.opencarebackend.dto.out.DoctorDTO;
import com.ciphertext.opencarebackend.exception.ResourceNotFoundException;
import com.ciphertext.opencarebackend.iservice.DoctorService;
import com.ciphertext.opencarebackend.model.Doctor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/api")
public class DoctorApiController {

    private static final Logger logger = LoggerFactory.getLogger(DoctorApiController.class);

    @Autowired
    DoctorService service;

    @GetMapping("/doctors")
    public List<DoctorDTO> getAllDoctors(Model model) {
        return service.getAllDoctors();
    }

    @GetMapping("/doctors/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable(value = "id") Long doctorId)
            throws ResourceNotFoundException {
        Doctor doctor = service.getDoctorById(doctorId);
        return ResponseEntity.ok().body(doctor);
    }
}
