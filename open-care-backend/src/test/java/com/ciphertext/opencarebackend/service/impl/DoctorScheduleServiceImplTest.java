package com.ciphertext.opencarebackend.service.impl;

import com.ciphertext.opencarebackend.exception.ResourceNotFoundException;
import com.ciphertext.opencarebackend.model.entity.DoctorSchedule;
import com.ciphertext.opencarebackend.repository.DoctorScheduleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DoctorScheduleServiceImplTest {

    @Mock
    DoctorScheduleRepository doctorScheduleRepository;

    @InjectMocks
    DoctorScheduleServiceImpl doctorScheduleService;


    @Test
    @DisplayName("Should throw a ResourceNotFoundException when the given id does not exist")
    void getDoctorScheduleByIdWhenIdDoesNotExistThenThrowException() {
        Long id = 1L;
        when(doctorScheduleRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            doctorScheduleService.getDoctorScheduleById(id);
        });

        verify(doctorScheduleRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Should return the doctor's schedule when the given id exists")
    void getDoctorScheduleByIdWhenIdExists() {
        Long id = 1L;
        DoctorSchedule doctorSchedule = new DoctorSchedule();
        doctorSchedule.setId(id);

        when(doctorScheduleRepository.findById(id)).thenReturn(Optional.of(doctorSchedule));

        DoctorSchedule result = doctorScheduleService.getDoctorScheduleById(id);

        assertEquals(doctorSchedule, result);
        verify(doctorScheduleRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Should return all doctor schedules")
    void getAllDoctorSchedules() {
        List<DoctorSchedule> doctorSchedules = new ArrayList<>();
        doctorSchedules.add(new DoctorSchedule());
        doctorSchedules.add(new DoctorSchedule());

        when(doctorScheduleRepository.findAll()).thenReturn(doctorSchedules);

        List<DoctorSchedule> result = doctorScheduleService.getAllDoctorSchedules();

        assertEquals(2, result.size());
        verify(doctorScheduleRepository, times(1)).findAll();
    }
}