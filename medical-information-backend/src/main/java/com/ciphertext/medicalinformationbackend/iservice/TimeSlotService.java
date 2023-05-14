package com.ciphertext.medicalinformationbackend.iservice;

import com.ciphertext.medicalinformationbackend.exception.RecordNotFoundException;
import com.ciphertext.medicalinformationbackend.model.TimeSlot;

import java.util.List;

/**
 * @author Sadman
 */
public interface TimeSlotService {
    List<TimeSlot> getAllTimeSlots();
    TimeSlot getTimeSlotById(int id) throws RecordNotFoundException;
}
