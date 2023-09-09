package com.ciphertext.opencarebackend.model.dto;

import com.ciphertext.opencarebackend.model.enums.HospitalType;
import com.ciphertext.opencarebackend.model.enums.OrganizationType;
import com.ciphertext.opencarebackend.model.entity.District;
import lombok.Getter;
import lombok.Setter;

/**
 * @author sadman @Date ২৭/৬/২৩
 */
@Getter
@Setter
public class InstitutionDTO {
    private int id;
    private String acronym;
    private String name;
    private String bnName;
    private Integer establishedYear;
    private Integer enroll;
    private District district;
    private HospitalType hospitalType;
    private OrganizationType organizationType;
    private String lat;
    private String lon;
    private String url;
}
