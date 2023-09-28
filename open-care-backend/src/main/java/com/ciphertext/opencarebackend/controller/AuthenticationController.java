package com.ciphertext.opencarebackend.controller;

import com.ciphertext.opencarebackend.annotations.SecureAPI;
import com.ciphertext.opencarebackend.model.dto.auth.LoginResponse;
import com.ciphertext.opencarebackend.model.dto.auth.ResetPasswordRequestDTO;
import com.ciphertext.opencarebackend.model.dto.auth.UserLogin;
import com.ciphertext.opencarebackend.service.AuthenticationService;
import com.ciphertext.opencarebackend.service.message.ApplicationMessageResolver;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
@Tag(name = "Authentication Manager")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final ApplicationMessageResolver messageResolver;

    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody UserLogin userLoginRequest) {
        LoginResponse loginResponse = authenticationService.login(userLoginRequest);
        return ResponseEntity.ok(loginResponse);
    }
    @PostMapping("refresh-token")
    @SecureAPI
    public ResponseEntity<LoginResponse> refreshToken(@Valid @RequestBody String refreshToken, HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null) {
            throw new AccessDeniedException(messageResolver.getMessage("auth.unauthorized"));
        }
        String token = authHeader.substring(7);
        LoginResponse loginResponse = authenticationService.createRefreshToken(refreshToken, token);
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("reset-password")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetPasswordRequestDTO resetPasswordRequestDTO) {
        authenticationService.resetPassword(resetPasswordRequestDTO);
        return ResponseEntity.ok(messageResolver.getMessage("auth.reset.password"));
    }

    @PostMapping("forget-password")
    public ResponseEntity<?> forgetPassword(@Valid @RequestBody String email) {
        authenticationService.forgetPassword(email);
        return ResponseEntity.ok(messageResolver.getMessage("auth.reset.email.success"));
    }

    @PostMapping("logout")
    @SecureAPI
    public ResponseEntity<?> logout(HttpServletRequest servletRequest) {
        String authHeader = servletRequest.getHeader("Authorization");
        if (authHeader == null) {
            throw new AccessDeniedException(messageResolver.getMessage("auth.unauthorized"));
        }
        String token = authHeader.substring(7);
        authenticationService.logout(token);
        return ResponseEntity.ok(messageResolver.getMessage("auth.logout"));
    }

}
