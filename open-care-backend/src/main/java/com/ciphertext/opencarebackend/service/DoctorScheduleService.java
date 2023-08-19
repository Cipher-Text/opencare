package com.ciphertext.opencarebackend.service;

import com.ciphertext.opencarebackend.exception.ResourceNotFoundException;
import com.ciphertext.opencarebackend.model.entity.DoctorSchedule;

import java.util.List;

public interface DoctorScheduleService {
    List<DoctorSchedule> getAllDoctorSchedules();
    DoctorSchedule getDoctorScheduleById(Long id) throws ResourceNotFoundException;
}
