package com.ciphertext.opencarebackend.model.dto;

import com.ciphertext.opencarebackend.model.enums.HospitalType;
import com.ciphertext.opencarebackend.model.enums.OrganizationType;
import com.ciphertext.opencarebackend.model.entity.District;
import com.ciphertext.opencarebackend.model.entity.Union;
import com.ciphertext.opencarebackend.model.entity.Upazila;
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
    private String bnName;
    private Integer numberOfBed;
    private District district;
    private Upazila upazila;
    private Union union;
    private HospitalType hospitalType;
    private OrganizationType organizationType;
    private String lat;
    private String lon;
    private String url;
}
