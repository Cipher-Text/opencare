package com.ciphertext.opencarebackend.controller;

import com.ciphertext.opencarebackend.annotations.SecureAPI;
import com.ciphertext.opencarebackend.model.dto.UserInfoDTO;
import com.ciphertext.opencarebackend.service.UserManagementService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user-management/")
@RequiredArgsConstructor
@Tag(name = "User Management Controller")
public class UserManagementController {
    private final UserManagementService userManagementService;
    @PostMapping("user")
    public ResponseEntity<?> createUser(@RequestBody UserInfoDTO userInfoDTO){
        userManagementService.createUser(userInfoDTO);
        return ResponseEntity.ok("user created successfully");
    }
}
