package com.ciphertext.opencarebackend.controller;

import com.ciphertext.opencarebackend.model.dto.auth.LoginResponse;
import com.ciphertext.opencarebackend.model.dto.auth.UserLogin;
import com.ciphertext.opencarebackend.service.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody UserLogin userLoginRequest) {
        LoginResponse loginResponse = authenticationService.login(userLoginRequest);
        return ResponseEntity.ok(loginResponse);
    }

}
