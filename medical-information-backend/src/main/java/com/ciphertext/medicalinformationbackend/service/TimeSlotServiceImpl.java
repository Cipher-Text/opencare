package com.ciphertext.medicalinformationbackend.service;

import com.ciphertext.medicalinformationbackend.exception.RecordNotFoundException;
import com.ciphertext.medicalinformationbackend.iservice.TimeSlotService;
import com.ciphertext.medicalinformationbackend.model.TimeSlot;
import com.ciphertext.medicalinformationbackend.repository.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sadman
 */
@Service
public class TimeSlotServiceImpl implements TimeSlotService {

    @Autowired
    TimeSlotRepository timeSlotRepository;

    @Override
    public List<TimeSlot> getAllTimeSlots() {
        return timeSlotRepository.findAll();
    }

    @Override
    public TimeSlot getTimeSlotById(int id) throws RecordNotFoundException {
        return timeSlotRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }
}
