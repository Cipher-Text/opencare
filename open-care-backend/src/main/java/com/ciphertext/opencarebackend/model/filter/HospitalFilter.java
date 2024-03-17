package com.ciphertext.opencarebackend.model.filter;

import com.ciphertext.opencarebackend.model.enums.HospitalType;
import com.ciphertext.opencarebackend.model.enums.OrganizationType;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class HospitalFilter {
    private String name;
    private String bnName;
    private Integer numberOfBed;
    private List<Integer> districtIds;
    private Integer upazilaId;
    private Integer unionId;
    private List<String> hospitalTypes;
    private String organizationType;
}
