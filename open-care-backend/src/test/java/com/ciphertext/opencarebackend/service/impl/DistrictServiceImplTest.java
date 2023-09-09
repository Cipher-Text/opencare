package com.ciphertext.opencarebackend.service.impl;

import com.ciphertext.opencarebackend.exception.ResourceNotFoundException;
import com.ciphertext.opencarebackend.model.entity.District;
import com.ciphertext.opencarebackend.model.entity.Division;
import com.ciphertext.opencarebackend.repository.DistrictRepository;
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
class DistrictServiceImplTest {
    @Mock
    private DistrictRepository districtRepository;

    @InjectMocks
    private DistrictServiceImpl districtService;


    @Test
    @DisplayName("Should throw ResourceNotFoundException when the id does not exist")
    void getDistrictByIdWhenIdDoesNotExistThenThrowResourceNotFoundException() {
        int id = 1;
        when(districtRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            districtService.getDistrictById(id);
        });

        verify(districtRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Should return the district when the id exists")
    void getDistrictByIdWhenIdExists() {
        int id = 1;
        District district = new District();
        district.setId(id);
        district.setName("Test District");
        district.setBnName("টেস্ট জেলা");
        district.setLat("12.3456");
        district.setLon("78.9012");
        district.setUrl("https://example.com");

        when(districtRepository.findById(id)).thenReturn(Optional.of(district));

        District result = districtService.getDistrictById(id);

        assertEquals(id, result.getId());
        assertEquals("Test District", result.getName());
        assertEquals("টেস্ট জেলা", result.getBnName());
        assertEquals("12.3456", result.getLat());
        assertEquals("78.9012", result.getLon());
        assertEquals("https://example.com", result.getUrl());

        verify(districtRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Should return an empty list when no districts are present in the repository")
    void getAllDistrictsWhenNoDistrictsArePresent() {
        when(districtRepository.findAll()).thenReturn(Collections.emptyList());

        List<District> districts = districtService.getAllDistricts();

        assertThat(districts).isEmpty();
        verify(districtRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return all districts when districts are present in the repository")
    void getAllDistrictsWhenDistrictsArePresent() {
        List<District> districts = new ArrayList<>();
        districts.add(new District(1, new Division(1, "Division 1", "বিভাগ ১", "division1.com"), "District 1", "জেলা ১", "lat1", "lon1", "district1.com"));
        districts.add(new District(2, new Division(2, "Division 2", "বিভাগ ২", "division2.com"), "District 2", "জেলা ২", "lat2", "lon2", "district2.com"));
        districts.add(new District(3, new Division(1, "Division 1", "বিভাগ ১", "division1.com"), "District 3", "জেলা ৩", "lat3", "lon3", "district3.com"));

        when(districtRepository.findAll()).thenReturn(districts);

        List<District> result = districtService.getAllDistricts();

        assertEquals(3, result.size());
        assertThat(result).containsExactlyInAnyOrderElementsOf(districts);
        verify(districtRepository, times(1)).findAll();
    }
}