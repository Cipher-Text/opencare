package com.ciphertext.opencarebackend.service;

import com.ciphertext.opencarebackend.model.dto.auth.LoginResponse;
import com.ciphertext.opencarebackend.model.dto.auth.ResetPasswordRequestDTO;
import com.ciphertext.opencarebackend.model.dto.auth.UserLogin;

public interface AuthenticationService {


    LoginResponse login(UserLogin userLoginRequest);

    void logout(String token);

    void forgetPassword(String email);

    void resetPassword(ResetPasswordRequestDTO resetPasswordRequestDTO);

    LoginResponse createRefreshToken(String refreshToken, String token);
}
