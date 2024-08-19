package com.ciphertext.opencarebackend.model.dto;

import lombok.Data;

@Data
public class UserInfoDTO {
    private String fullName;
    private String userName;
    private String password;
    private String email;
    private String phone;
    private String role;
}
