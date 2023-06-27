package com.ciphertext.opencarebackend.dto.out;

import com.ciphertext.opencarebackend.enums.HospitalType;
import com.ciphertext.opencarebackend.enums.OrganizationType;
import com.ciphertext.opencarebackend.model.District;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
