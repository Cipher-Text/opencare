package com.ciphertext.opencarebackend.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MedicalTestDTO {
    private Integer id;
    private Integer parentId;
    private String name;
    private String bnName;
    private List<String> alternativeNames;
    private String description;
}
