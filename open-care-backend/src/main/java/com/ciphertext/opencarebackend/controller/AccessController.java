package com.ciphertext.opencarebackend.controller;

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
        return ResponseEntity.ok("permission.add");
    }

    @DeleteMapping("permission/delete/{permission}")
    public ResponseEntity<?> revokeRolePermission(@PathVariable("permission") String permission) {
        accessManageService.deletePermission(permission);
        return ResponseEntity.ok(messageResolver.getMessage("permission.remove"));
    }

    @DeleteMapping("permission/{role}/{permission}")
    public ResponseEntity<?> deletePermission(@PathVariable("role") String role, @PathVariable("permission") String permission) {
        accessManageService.revokePermissionFromRole(permission,role);
        return ResponseEntity.ok(messageResolver.getMessage("permission.remove"));
    }

}
