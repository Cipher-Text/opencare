package com.ciphertext.opencarebackend.service;

import com.ciphertext.opencarebackend.model.dto.DoctorDTO;
import com.ciphertext.opencarebackend.exception.ResourceNotFoundException;
import com.ciphertext.opencarebackend.model.dto.DoctorDTO;
import com.ciphertext.opencarebackend.model.entity.Doctor;
import com.ciphertext.opencarebackend.model.entity.Doctor;
import com.ciphertext.opencarebackend.model.filter.DoctorFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author Sadman
 */
public interface DoctorService {
    List<DoctorDTO> getAllDoctors();
    Page<DoctorDTO> getPaginatedDataWithFilters(DoctorFilter doctorFilter, Pageable pagingSort);
    Doctor getDoctorById(Long id) throws ResourceNotFoundException;
    DoctorDTO createDoctor(DoctorDTO doctorDTO);
    Doctor updateDoctor(Doctor newDoctor, Long doctorId);
    ResponseEntity<Object> deleteDoctorById(Long doctorId);
}
