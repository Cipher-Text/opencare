package com.ciphertext.opencarebackend.controller;

import com.ciphertext.opencarebackend.annotations.SecureAPI;
import com.ciphertext.opencarebackend.exception.ResourceNotFoundException;
import com.ciphertext.opencarebackend.service.LocationService;
import com.ciphertext.opencarebackend.model.entity.District;
import com.ciphertext.opencarebackend.model.entity.Division;
import com.ciphertext.opencarebackend.model.entity.Union;
import com.ciphertext.opencarebackend.model.entity.Upazila;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Sadman
 */
@RestController
@RequestMapping("/api")
@SecureAPI
@CrossOrigin(origins = "http://localhost:3000")
public class LocationApiController {
    @Autowired
    LocationService service;

    @GetMapping("/divisions")
    public List<Division> getAllDivisions(Model model) {
        return service.getAllDivisions();
    }

    @GetMapping("/divisions/{id}")
    public ResponseEntity<Division> getDivisionById(@PathVariable(value = "id") int divisionId)
            throws ResourceNotFoundException {
        Division division = service.getDivisionById(divisionId);
        return ResponseEntity.ok().body(division);
    }

    @GetMapping("/divisions/{id}/districts")
    public List<District> getAllDistrictsByDivisionId(@PathVariable(value = "id") int divisionId) throws ResourceNotFoundException {
        return service.getAllDistrictsByDivisionId(divisionId);
    }

    @GetMapping("/districts")
    public List<District> getAllDistricts(Model model) {
        return service.getAllDistricts();
    }

    @GetMapping("/districts/{id}")
    public ResponseEntity<District> getDistrictById(@PathVariable(value = "id") int districtId)
            throws ResourceNotFoundException {
        District district = service.getDistrictById(districtId);
        return ResponseEntity.ok().body(district);
    }

    @GetMapping("/districts/{id}/upazilas")
    public List<Upazila> getAllUpazilasByDistrictId(@PathVariable(value = "id") int districtId) throws ResourceNotFoundException {
        return service.getAllUpazilasByDistrictId(districtId);
    }

    @GetMapping("/upazilas")
    public List<Upazila> getAllUpazilas(Model model) {
        return service.getAllUpazilas();
    }

    @GetMapping("/upazilas/{id}")
    public ResponseEntity<Upazila> getUpazilaById(@PathVariable(value = "id") int upazilaId)
            throws ResourceNotFoundException {
        Upazila upazila = service.getUpazilaById(upazilaId);
        return ResponseEntity.ok().body(upazila);
    }

    @GetMapping("/upazilas/{id}/unions")
    public List<Union> getAllUnionByUpazilaId(@PathVariable(value = "id") int upazilaId) throws ResourceNotFoundException {
        return service.getAllUnionsByUpazilaId(upazilaId);
    }
}
