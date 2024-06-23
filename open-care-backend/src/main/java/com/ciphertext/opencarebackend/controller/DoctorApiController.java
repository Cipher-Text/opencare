package com.ciphertext.opencarebackend.controller;

import com.ciphertext.opencarebackend.exception.ResourceNotFoundException;
import com.ciphertext.opencarebackend.model.dto.DoctorDTO;
import com.ciphertext.opencarebackend.model.entity.Doctor;
import com.ciphertext.opencarebackend.model.filter.DoctorFilter;
import com.ciphertext.opencarebackend.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sadman
 */
@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorApiController {

    private final DoctorService service;

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getAllDoctorsPage(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String bnName,
            @RequestParam(required = false) Integer districtId,
            @RequestParam(required = false) Integer upazillaId,
            @RequestParam(required = false) Integer unionId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder) {

        Sort sort = Sort.by(sortOrder.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);

        Pageable pagingSort = PageRequest.of(page, size, sort);
        DoctorFilter doctorFilter = DoctorFilter.builder()
                .name(name)
                .bnName(bnName)
                .districtId(districtId)
                .upazilaId(upazillaId)
                .unionId(unionId)
                .build();
        Page<DoctorDTO> pageDoctors = service.getPaginatedDataWithFilters(doctorFilter, pagingSort);

        Map<String, Object> response = new HashMap<>();
        response.put("doctors", pageDoctors.getContent());
        response.put("currentPage", pageDoctors.getNumber());
        response.put("totalItems", pageDoctors.getTotalElements());
        response.put("totalPages", pageDoctors.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable(value = "id") Long doctorId)
            throws ResourceNotFoundException {
        Doctor doctor = service.getDoctorById(doctorId);
        return ResponseEntity.ok().body(doctor);
    }

    @PostMapping("")
    public Doctor createDoctor(@Valid @RequestBody Doctor doctor) {
        return service.createDoctor(doctor);
    }

    @PutMapping("/edit/{id}")
    public Doctor editDoctorById(@RequestBody Doctor newDoctor, @PathVariable(value = "id") Long doctorId) {
        return service.updateDoctor(newDoctor, doctorId);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<Object> deleteDoctorsById(@PathVariable(value = "id") Long doctorId) {
        return service.deleteDoctorById(doctorId);
    }
}
