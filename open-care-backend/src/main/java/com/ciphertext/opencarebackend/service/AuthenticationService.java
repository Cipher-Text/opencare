package com.ciphertext.opencarebackend.service;

import com.ciphertext.opencarebackend.model.dto.auth.LoginResponse;
import com.ciphertext.opencarebackend.model.dto.auth.UserLogin;

public interface AuthenticationService {


    LoginResponse login(UserLogin userLoginRequest);
}
