package com.ciphertext.opencarebackend.service.impl;

import com.ciphertext.opencarebackend.exception.ResourceNotFoundException;
import com.ciphertext.opencarebackend.service.DoctorScheduleService;
import com.ciphertext.opencarebackend.model.entity.DoctorSchedule;
import com.ciphertext.opencarebackend.repository.DoctorScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sadman
 */
@Service
public class DoctorScheduleServiceImpl implements DoctorScheduleService {

    @Autowired
    DoctorScheduleRepository DoctorScheduleRepository;

    @Override
    public List<DoctorSchedule> getAllDoctorSchedules() {
        return DoctorScheduleRepository.findAll();
    }

    @Override
    public DoctorSchedule getDoctorScheduleById(Long id) throws ResourceNotFoundException {
        return DoctorScheduleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found DoctorSchedule with id = " + id));
    }
}
