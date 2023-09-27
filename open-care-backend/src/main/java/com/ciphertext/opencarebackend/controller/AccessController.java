package com.ciphertext.opencarebackend.controller;

import com.ciphertext.opencarebackend.annotations.ADMIN;
import com.ciphertext.opencarebackend.annotations.SecureAPI;
import com.ciphertext.opencarebackend.service.AccessManageService;
import com.ciphertext.opencarebackend.service.message.ApplicationMessageResolver;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/access-control/")
@RequiredArgsConstructor
@Tag(name = "Access Management Controller")
@SecureAPI
public class AccessController {
    private final AccessManageService accessManageService;
    private final ApplicationMessageResolver messageResolver;
    @GetMapping("permission/{role}")
    public ResponseEntity<?> getRolePermission(@PathVariable("role") String roleName) {
        return ResponseEntity.ok(accessManageService.getRolePermission(roleName));
    }

    @GetMapping("permission")
    public ResponseEntity<?> getRolePermission() {
        return ResponseEntity.ok(accessManageService.getRolePermission());
    }

    @PostMapping("permission/{role}/{permission}")
    public ResponseEntity<?> addRolePermission(@PathVariable("role") String role, @PathVariable("permission") String permission) {
        accessManageService.addPermissionToRole(permission,role);
        return ResponseEntity.ok("");
    }

    @DeleteMapping("permission/{role}/{permission}")
    @ADMIN
    public ResponseEntity<?> removeRolePermission(@PathVariable("role") String role, @PathVariable("permission") String permission) {
        accessManageService.removePermissionFromRole(permission,role);
        return ResponseEntity.ok(messageResolver.getMessage("permission.remove"));
    }

}
