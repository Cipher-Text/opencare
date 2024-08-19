package com.ciphertext.opencarebackend.controller;

import com.ciphertext.opencarebackend.model.dto.HospitalTypeDTO;
import com.ciphertext.opencarebackend.model.dto.OrganizationTypeDTO;
import com.ciphertext.opencarebackend.model.enums.HospitalType;
import com.ciphertext.opencarebackend.model.enums.OrganizationType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class EnumApiController {

    @GetMapping("/hospital-types")
    public List<HospitalTypeDTO> getAllHospitalTypes() {
        return Arrays.stream(HospitalType.values())
                .map(hospitalType -> HospitalTypeDTO.builder()
                        .name(hospitalType.name())
                        .bnName(hospitalType.getBanglaName())
                        .build())
                .collect(Collectors.toList());
    }

    @GetMapping("/organization-types")
    public List<OrganizationTypeDTO> getAllOrganizationTypes() {
        return Arrays.stream(OrganizationType.values())
                .map(organizationType -> OrganizationTypeDTO.builder()
                        .name(organizationType.name())
                        .bnName(organizationType.getBanglaName())
                        .build())
                .collect(Collectors.toList());
    }

}
