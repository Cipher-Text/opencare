package com.ciphertext.opencarebackend.model.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Sadman
 */
@Getter
@Setter
public class MedicalSpecialityDTO {
    private Integer id;
    private Integer parentMedicalSpecialityId;
    private String name;
    private String bnName;
    private String description;
}
