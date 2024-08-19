package com.ciphertext.opencarebackend.service.impl;

import com.ciphertext.opencarebackend.exception.ResourceNotFoundException;
import com.ciphertext.opencarebackend.model.entity.Degree;
import com.ciphertext.opencarebackend.model.enums.DegreeType;
import com.ciphertext.opencarebackend.repository.DegreeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DoctorDegreeServiceImplTest {

    @Mock
    DegreeRepository degreeRepository;

    @InjectMocks
    DoctorDegreeServiceImpl degreeService;


    @Test
    @DisplayName("Should throw ResourceNotFoundException when the id does not exist")
    void getDegreeByIdWhenIdDoesNotExistThenThrowResourceNotFoundException() {
        int id = 1;
        when(degreeRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            degreeService.getDegreeById(id);
        });

        verify(degreeRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Should return the degree when the id exists")
    void getDegreeByIdWhenIdExists() {
        int id = 1;
        Degree degree = new Degree();
        degree.setId(id);
        degree.setName("Bachelor of Science");
        degree.setAbbreviation("BSc");
        degree.setDegreeType(DegreeType.UNDERGRADUATE);

        when(degreeRepository.findById(id)).thenReturn(Optional.of(degree));

        Degree result = degreeService.getDegreeById(id);

        assertEquals(degree, result);
        verify(degreeRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Should return an empty list when no degrees are present in the repository")
    void getAllDegreesWhenNoDegreesArePresent() {
        when(degreeRepository.findAll()).thenReturn(Collections.emptyList());

        List<Degree> degrees = degreeService.getAllDegrees();

        assertThat(degrees).isEmpty();
        verify(degreeRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return all degrees when degrees are present in the repository")
    void getAllDegreesWhenDegreesArePresent() {
        List<Degree> degrees = new ArrayList<>();
        degrees.add(new Degree(1, "Bachelor of Science", "BSc", DegreeType.UNDERGRADUATE));
        degrees.add(new Degree(2, "Master of Arts", "MA", DegreeType.GRADUATE));
        degrees.add(new Degree(3, "Doctor of Philosophy", "PhD", DegreeType.POSTGRADUATE));

        when(degreeRepository.findAll()).thenReturn(degrees);

        List<Degree> result = degreeService.getAllDegrees();

        assertEquals(degrees.size(), result.size());
        assertThat(result).containsAll(degrees);
        verify(degreeRepository, times(1)).findAll();
    }
}