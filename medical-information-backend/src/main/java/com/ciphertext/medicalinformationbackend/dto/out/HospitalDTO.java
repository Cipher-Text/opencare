package com.ciphertext.medicalinformationbackend.dto.out;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Sadman
 */
@Getter
@Setter
public class HospitalDTO {
    private int id;
    private String name;
    private String district;
    private String type;
    private int numberOfBed;
}
