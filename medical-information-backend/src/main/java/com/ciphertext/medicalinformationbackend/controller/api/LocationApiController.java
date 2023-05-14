package com.ciphertext.medicalinformationbackend.controller.api;

import com.ciphertext.medicalinformationbackend.exception.RecordNotFoundException;
import com.ciphertext.medicalinformationbackend.iservice.LocationService;
import com.ciphertext.medicalinformationbackend.model.District;
import com.ciphertext.medicalinformationbackend.model.Division;
import com.ciphertext.medicalinformationbackend.model.Upazila;
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
public class LocationApiController {
    @Autowired
    LocationService service;

    @GetMapping("/divisions")
    public List<Division> getAllDivisions(Model model) {
        return service.getAllDivisions();
    }

    @GetMapping("/divisions/{id}")
    public ResponseEntity<Division> getDivisionById(@PathVariable(value = "id") int divisionId)
            throws RecordNotFoundException {
        Division division = service.getDivisionById(divisionId);
        return ResponseEntity.ok().body(division);
    }

    @GetMapping("/divisions/{id}/districts")
    public List<District> getAllDistrictsByDivisionId(@PathVariable(value = "id") int divisionId) throws RecordNotFoundException {
        return service.getAllDistrictsByDivisionId(divisionId);
    }

    @GetMapping("/districts")
    public List<District> getAllDistricts(Model model) {
        return service.getAllDistricts();
    }

    @GetMapping("/districts/{id}")
    public ResponseEntity<District> getDistrictById(@PathVariable(value = "id") int districtId)
            throws RecordNotFoundException {
        District district = service.getDistrictById(districtId);
        return ResponseEntity.ok().body(district);
    }

    @GetMapping("/districts/{id}/upazilas")
    public List<Upazila> getAllUpazilasByDistrictId(@PathVariable(value = "id") int districtId) throws RecordNotFoundException {
        return service.getAllUpazilasByDistrictId(districtId);
    }

    @GetMapping("/upazilas")
    public List<Upazila> getAllUpazilas(Model model) {
        return service.getAllUpazilas();
    }

    @GetMapping("/upazilas/{id}")
    public ResponseEntity<Upazila> getUpazilaById(@PathVariable(value = "id") int upazilaId)
            throws RecordNotFoundException {
        Upazila upazila = service.getUpazilaById(upazilaId);
        return ResponseEntity.ok().body(upazila);
    }
}
