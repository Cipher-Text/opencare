package com.ciphertext.opencarebackend.model.dto.auth;

import lombok.Data;

@Data
public class UserLogin {
    private String username;
    private String password;
}
