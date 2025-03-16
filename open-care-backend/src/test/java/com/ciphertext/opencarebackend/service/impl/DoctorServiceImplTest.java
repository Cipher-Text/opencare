package com.ciphertext.opencarebackend.service.impl;

import com.ciphertext.opencarebackend.exception.ResourceNotFoundException;
import com.ciphertext.opencarebackend.model.dto.DoctorDTO;
import com.ciphertext.opencarebackend.model.entity.Doctor;
import com.ciphertext.opencarebackend.repository.DoctorRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DoctorServiceImplTest {

    @Mock
    DoctorRepository doctorRepository;

    @InjectMocks
    DoctorServiceImpl doctorService;


    @Test
    @DisplayName("Should throw ResourceNotFoundException when the id does not exist")
    void getDoctorByIdWhenIdDoesNotExistThenThrowResourceNotFoundException() {
        Long id = 1L;
        when(doctorRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> doctorService.getDoctorById(id));

        verify(doctorRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Should return the doctor when the id exists")
    void getDoctorByIdWhenIdExists() {
        Long id = 1L;
        Doctor doctor = new Doctor();
        doctor.setId(id);
        doctor.setName("John Doe");
        doctor.setDescription("Lorem ipsum dolor sit amet");
        when(doctorRepository.findById(id)).thenReturn(Optional.of(doctor));

        Doctor result = doctorService.getDoctorById(id);

        assertEquals(doctor, result);
        verify(doctorRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Should return empty list when there are no doctors in the repository")
    void getAllDoctorsWhenNoDoctorsInRepository() {
        when(doctorRepository.findAll()).thenReturn(Collections.emptyList());

        List<DoctorDTO> result = doctorService.getAllDoctors();

        assertThat(result).isEmpty();
        verify(doctorRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return all doctors from the repository")
    void getAllDoctors() {
        Doctor doctor1 = new Doctor();
        doctor1.setId(1L);
        doctor1.setName("John Doe");
        doctor1.setDescription("General Physician");

        Doctor doctor2 = new Doctor();
        doctor2.setId(2L);
        doctor2.setName("Jane Smith");
        doctor2.setDescription("Dermatologist");

        List<Doctor> doctors = List.of(doctor1, doctor2);

        when(doctorRepository.findAll()).thenReturn(doctors);

        List<DoctorDTO> doctorDTOs = doctorService.getAllDoctors();

        assertEquals(2, doctorDTOs.size());
        assertEquals(1L, doctorDTOs.get(0).getId());
        assertEquals("John Doe", doctorDTOs.get(0).getName());
        assertEquals(15, doctorDTOs.get(0).getYearOfExperience());
        assertEquals("General Physician", doctorDTOs.get(0).getDescription());

        assertEquals(2L, doctorDTOs.get(1).getId());
        assertEquals("Jane Smith", doctorDTOs.get(1).getName());
        assertEquals(15, doctorDTOs.get(1).getYearOfExperience());
        assertEquals("Dermatologist", doctorDTOs.get(1).getDescription());

        verify(doctorRepository, times(1)).findAll();
    }
}