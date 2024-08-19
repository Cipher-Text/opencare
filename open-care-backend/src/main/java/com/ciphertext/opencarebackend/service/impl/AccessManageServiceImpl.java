package com.ciphertext.opencarebackend.service.impl;

import com.ciphertext.opencarebackend.exception.ResourceNotFoundException;
import com.ciphertext.opencarebackend.model.entity.Permission;
import com.ciphertext.opencarebackend.model.entity.Role;
import com.ciphertext.opencarebackend.repository.PermissionRepository;
import com.ciphertext.opencarebackend.repository.RoleRepository;
import com.ciphertext.opencarebackend.security.jwt.JWTTokenService;
import com.ciphertext.opencarebackend.service.AccessManageService;
import com.ciphertext.opencarebackend.service.message.ApplicationMessageResolver;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccessManageServiceImpl implements AccessManageService {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final HttpServletRequest servletRequest;
    private final ApplicationMessageResolver messageResolver;
    private final JWTTokenService jwtTokenService;
    @Override
    public List<String> getRolePermission(String roleName) {
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new ResourceNotFoundException(messageResolver.getMessage("role.not.found")));
        return role.getPermissions()
                .stream().map(Permission::getName).toList();
    }

    @Override
    public List<String> getRolePermission() {
        String jwt = servletRequest.getHeader("Authorization").substring(7);
        List<String> roles = jwtTokenService.extractAuthorities(jwt);
        return roleRepository.findByNameIn(roles)
                .stream().map(Role::getPermissions)
                .flatMap(Collection::stream)
                .map(Permission::getName)
                .toList();

    }

    @Override
    public void addPermissionToRole(String permissionName, String roleName) {
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new ResourceNotFoundException(messageResolver.getMessage("role.not.found")));
        Permission permission = permissionRepository.findByName(permissionName).orElse(new Permission(permissionName));
        role.addPermission(permission);
        roleRepository.save(role);
    }

    @Override
    public void revokePermissionFromRole(String permissionName, String roleName) {
        Permission permission = permissionRepository.findByName(permissionName)
                .orElseThrow(()-> new ResourceNotFoundException(messageResolver.getMessage("permission.not.found")));
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new ResourceNotFoundException(messageResolver.getMessage("role.not.found")));
        role.removePermission(permission);
        roleRepository.save(role);
    }

    @Override
    public void deletePermission(String permissionName) {
        Permission permission = permissionRepository.findByName(permissionName)
                .orElseThrow(()-> new ResourceNotFoundException(messageResolver.getMessage("permission.not.found")));
        if(!permission.getRoles().isEmpty()){
            String usedPermission = permission.getRoles().stream()
                    .map(Role::getName)
                    .collect(Collectors.joining(","));
            if(!usedPermission.isEmpty()){
                throw new ResourceNotFoundException("can't delete permission" +permissionName + "as this is used in role "+usedPermission);
            }

        }
        permissionRepository.delete(permission);
    }

}
