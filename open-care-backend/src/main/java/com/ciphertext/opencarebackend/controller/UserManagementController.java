package com.ciphertext.opencarebackend.controller;

import com.ciphertext.opencarebackend.annotations.ADMIN;
import com.ciphertext.opencarebackend.annotations.SUPERADMIN;
import com.ciphertext.opencarebackend.annotations.SecureAPI;
import com.ciphertext.opencarebackend.model.dto.UserInfoDTO;
import com.ciphertext.opencarebackend.service.UserManagementService;
import com.ciphertext.opencarebackend.service.message.ApplicationMessageResolver;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-management/")
@RequiredArgsConstructor
@Tag(name = "User Management Controller")
public class UserManagementController {
    private final UserManagementService userManagementService;
    private final ApplicationMessageResolver messageResolver;
    @PostMapping("user")
    public ResponseEntity<?> createUser(@RequestBody UserInfoDTO userInfoDTO){
        userManagementService.createUser(userInfoDTO);
        return ResponseEntity.ok(messageResolver.getMessage("user.create"));
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<?> getUserInfo(@PathVariable("userId") Long userId){
        return ResponseEntity.ok(userManagementService.getUserInfo(userId));
    }


    @GetMapping("user/roles")
    @SecureAPI
    public ResponseEntity<?> getUserRole(){
       return ResponseEntity.ok(userManagementService.getUserRole());
    }

    @PostMapping("user/roles/{userid}/{role}")
    @SecureAPI
    @SUPERADMIN
    public ResponseEntity<?> addRoleToUser(@PathVariable("userid") Long userId,@PathVariable("role") String role){
        userManagementService.addRoleToUser(userId,role);
        return ResponseEntity.ok(messageResolver.getMessage("user.role.add"));
    }
    @PostMapping("user/activate/{userid}")
    @SecureAPI
    @ADMIN
    public ResponseEntity<?> activateUser(@PathVariable("userid") Long userId){
        userManagementService.activateUser(userId);
        return ResponseEntity.ok(messageResolver.getMessage("user.activate"));
    }

}
