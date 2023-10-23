package com.ciphertext.opencarebackend.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrganizationTypeDTO {
    private String name;
    private String bnName;
}
