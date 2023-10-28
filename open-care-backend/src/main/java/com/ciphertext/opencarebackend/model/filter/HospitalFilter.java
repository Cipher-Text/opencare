package com.ciphertext.opencarebackend.model.filter;

import com.ciphertext.opencarebackend.model.enums.HospitalType;
import com.ciphertext.opencarebackend.model.enums.OrganizationType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class HospitalFilter {
    private String name;
    private String bnName;
    private Integer numberOfBed;
    private Integer districtId;
    private Integer upazilaId;
    private Integer unionId;
    private String hospitalType;
    private String organizationType;
}
