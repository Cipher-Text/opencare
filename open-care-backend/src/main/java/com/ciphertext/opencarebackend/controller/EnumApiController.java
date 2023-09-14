package com.ciphertext.opencarebackend.controller;

import com.ciphertext.opencarebackend.model.dto.DoctorDTO;
import com.ciphertext.opencarebackend.model.dto.HospitalTypeDTO;
import com.ciphertext.opencarebackend.model.enums.HospitalType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class EnumApiController {

    @GetMapping("/hospital-types")
    public List<HospitalTypeDTO> getAllHospitalTypes() {
        return Arrays.stream(HospitalType.values())
                .map(hospitalType -> HospitalTypeDTO.builder()
                        .name(hospitalType.name())
                        .bnName(hospitalType.getBenglaName())
                        .build())
                .collect(Collectors.toList());
    }

}
