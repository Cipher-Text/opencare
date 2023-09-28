package com.ciphertext.opencarebackend.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class RoleDTO {
    private String roleName;
    private List<String> permissions;
}
