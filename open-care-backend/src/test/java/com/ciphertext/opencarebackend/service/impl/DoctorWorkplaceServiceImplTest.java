package com.ciphertext.opencarebackend.service.impl;

import com.ciphertext.opencarebackend.exception.ResourceNotFoundException;
import com.ciphertext.opencarebackend.model.entity.DoctorWorkplace;
import com.ciphertext.opencarebackend.repository.DoctorWorkplaceRepository;
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
class DoctorWorkplaceServiceImplTest {

    @Mock
    DoctorWorkplaceRepository doctorWorkplaceRepository;

    @InjectMocks
    DoctorWorkplaceServiceImpl doctorWorkplaceService;


    @Test
    @DisplayName("Should throw a ResourceNotFoundException when the given id does not exist")
    void getDoctorWorkplaceByIdWhenIdDoesNotExistThenThrowException() {
        long id = 1;
        when(doctorWorkplaceRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            doctorWorkplaceService.getDoctorWorkplaceById(id);
        });

        verify(doctorWorkplaceRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Should return the doctor's workplace when the given id exists")
    void getDoctorWorkplaceByIdWhenIdExists() {
        long id = 1;
        DoctorWorkplace doctorWorkplace = new DoctorWorkplace();
        doctorWorkplace.setId(id);
        when(doctorWorkplaceRepository.findById(id)).thenReturn(Optional.of(doctorWorkplace));

        DoctorWorkplace result = doctorWorkplaceService.getDoctorWorkplaceById(id);

        assertEquals(doctorWorkplace, result);
        verify(doctorWorkplaceRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Should return all doctor workplaces")
    void getAllDoctorWorkplaces() {
        List<DoctorWorkplace> doctorWorkplaces = new ArrayList<>();
        doctorWorkplaces.add(new DoctorWorkplace());
        doctorWorkplaces.add(new DoctorWorkplace());
        doctorWorkplaces.add(new DoctorWorkplace());

        when(doctorWorkplaceRepository.findAll()).thenReturn(doctorWorkplaces);

        List<DoctorWorkplace> result = doctorWorkplaceService.getAllDoctorWorkplaces();

        assertEquals(3, result.size());
        verify(doctorWorkplaceRepository, times(1)).findAll();
    }
}