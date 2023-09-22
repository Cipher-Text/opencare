package com.ciphertext.opencarebackend.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class HospitalTypeDTO {
    private String name;
    private String bnName;
}
