package com.ciphertext.opencarebackend.model.filter;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DoctorFilter {
    private String name;
    private String bnName;
    private String bmdcNo;
    private Integer districtId;
    private Integer upazilaId;
    private Integer unionId;
    private Integer specialityId;
    private Integer degreeId;
    private Integer hospitalId;
}
