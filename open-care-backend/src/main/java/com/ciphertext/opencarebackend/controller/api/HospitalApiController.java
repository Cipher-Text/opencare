package com.ciphertext.opencarebackend.controller.api;

import com.ciphertext.opencarebackend.dto.out.HospitalDTO;
import com.ciphertext.opencarebackend.exception.ResourceNotFoundException;
import com.ciphertext.opencarebackend.iservice.HospitalService;
import com.ciphertext.opencarebackend.model.Hospital;
import com.ciphertext.opencarebackend.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Sort.Order;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Sadman
 */
@RestController
@RequestMapping("/api")
public class HospitalApiController {
    @Autowired
    HospitalService service;

    @Autowired
    HospitalRepository hospitalRepository;

    @GetMapping("/hospitals")
    public ResponseEntity<Map<String, Object>> getAllHospitalsPage(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String bnName,
            @RequestParam(required = false) Integer numberOfBed,
            @RequestParam(required = false) Integer districtId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pagingSort = PageRequest.of(page, size);
        Page<Hospital> pageHospitals = hospitalRepository.getFilteredHospitals(name, bnName, numberOfBed, districtId, pagingSort);

        Map<String, Object> response = new HashMap<>();
        response.put("hospitals", pageHospitals.getContent());
        response.put("currentPage", pageHospitals.getNumber());
        response.put("totalItems", pageHospitals.getTotalElements());
        response.put("totalPages", pageHospitals.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/hospitals/{id}")
    public ResponseEntity<Hospital> getHospitalById(@PathVariable(value = "id") int hospitalId)
            throws ResourceNotFoundException {
        Hospital hospital = service.getHospitalById(hospitalId);
        return ResponseEntity.ok().body(hospital);
    }

    @PostMapping("/hospitals")
    public Hospital createHospital(@Valid @RequestBody Hospital hospital) {
        return service.createHospital(hospital);
    }

    @PutMapping("/hospitals/edit/{id}")
    public Hospital editHospitalById(@RequestBody Hospital newHospital, @PathVariable(value = "id") int hospitalId) {
        return service.updateHospital(newHospital, hospitalId);
    }

    @DeleteMapping("/hospitals/delete/{id}")
    @ResponseBody
    public ResponseEntity<Object> deleteHospitalsById(@PathVariable(value = "id") int hospitalId) {
        return service.deleteHospitalById(hospitalId);
    }
}
