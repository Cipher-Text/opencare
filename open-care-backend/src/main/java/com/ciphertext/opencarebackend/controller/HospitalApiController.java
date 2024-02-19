package com.ciphertext.opencarebackend.controller;

import com.ciphertext.opencarebackend.annotations.SecureAPI;
import com.ciphertext.opencarebackend.exception.ResourceNotFoundException;
import com.ciphertext.opencarebackend.model.dto.HospitalDTO;
import com.ciphertext.opencarebackend.model.filter.HospitalFilter;
import com.ciphertext.opencarebackend.service.HospitalService;
import com.ciphertext.opencarebackend.model.entity.Hospital;
import com.ciphertext.opencarebackend.repository.HospitalRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


import java.util.HashMap;
import java.util.Map;

/**
 * @author Sadman
 */
@RestController
@RequestMapping("/api/hospitals")
@SecureAPI
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class HospitalApiController {

    private final HospitalService service;

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getAllHospitalsPage(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String bnName,
            @RequestParam(required = false) Integer numberOfBed,
            @RequestParam(required = false) Integer districtId,
            @RequestParam(required = false) Integer upazillaId,
            @RequestParam(required = false) Integer unionId,
            @RequestParam(required = false) String hospitalType,
            @RequestParam(required = false) String organizationType,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pagingSort = PageRequest.of(page, size);
        HospitalFilter hospitalFilter = HospitalFilter.builder()
                .name(name)
                .bnName(bnName)
                .numberOfBed(numberOfBed)
                .districtId(districtId)
                .upazilaId(upazillaId)
                .unionId(unionId)
                .hospitalType(hospitalType)
                .organizationType(organizationType)
                .build();
        Page<HospitalDTO> pageHospitals = service.getPaginatedDataWithFilters(hospitalFilter, pagingSort);

        Map<String, Object> response = new HashMap<>();
        response.put("hospitals", pageHospitals.getContent());
        response.put("currentPage", pageHospitals.getNumber());
        response.put("totalItems", pageHospitals.getTotalElements());
        response.put("totalPages", pageHospitals.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hospital> getHospitalById(@PathVariable(value = "id") int hospitalId)
            throws ResourceNotFoundException {
        Hospital hospital = service.getHospitalById(hospitalId);
        return ResponseEntity.ok().body(hospital);
    }

    @PostMapping("")
    public Hospital createHospital(@Valid @RequestBody Hospital hospital) {
        return service.createHospital(hospital);
    }

    @PutMapping("/edit/{id}")
    public Hospital editHospitalById(@RequestBody Hospital newHospital, @PathVariable(value = "id") int hospitalId) {
        return service.updateHospital(newHospital, hospitalId);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<Object> deleteHospitalsById(@PathVariable(value = "id") int hospitalId) {
        return service.deleteHospitalById(hospitalId);
    }
}
