package com.ciphertext.opencarebackend.model.dto.auth;

import lombok.Data;

@Data
public class ResetPasswordRequestDTO {
    private String token;
    private String password;
}
