package com.ciphertext.medicalinformationbackend.controller.api;

import com.ciphertext.medicalinformationbackend.exception.RecordNotFoundException;
import com.ciphertext.medicalinformationbackend.iservice.InstitutionService;
import com.ciphertext.medicalinformationbackend.iservice.LocationService;
import com.ciphertext.medicalinformationbackend.model.District;
import com.ciphertext.medicalinformationbackend.model.Division;
import com.ciphertext.medicalinformationbackend.model.Institution;
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
@RequestMapping("/api/v1")
public class InstitutionApiController {
    @Autowired
    InstitutionService service;

    @GetMapping("/institutions")
    public List<Institution> getAllDivisions(Model model) {
        return service.getAllInstitutions();
    }

    @GetMapping("/institutions/{id}")
    public ResponseEntity<Institution> getDivisionById(@PathVariable(value = "id") int institutionId)
            throws RecordNotFoundException {
        Institution institution = service.getInstitutionById(institutionId);
        return ResponseEntity.ok().body(institution);
    }
}
