package com.ciphertext.opencarebackend.service.impl;

import com.ciphertext.opencarebackend.exception.ResourceNotFoundException;
import com.ciphertext.opencarebackend.model.dto.RoleDTO;
import com.ciphertext.opencarebackend.model.dto.UserInfoDTO;
import com.ciphertext.opencarebackend.model.entity.*;
import com.ciphertext.opencarebackend.repository.RoleRepository;
import com.ciphertext.opencarebackend.repository.UserRepository;
import com.ciphertext.opencarebackend.security.jwt.JWTTokenService;
import com.ciphertext.opencarebackend.service.UserManagementService;
import com.ciphertext.opencarebackend.service.event.UserActivationEvent;
import com.ciphertext.opencarebackend.service.event.listener.UserActivationListener;
import com.ciphertext.opencarebackend.service.message.ApplicationMessageResolver;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserManagementServiceImpl implements UserManagementService {
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final JWTTokenService jwtTokenService;
    private final HttpServletRequest servletRequest;
    private final ApplicationMessageResolver messageResolver;
    private final UserActivationListener eventListener;

    @Override
    public List<RoleDTO> getUserRole() {
        String jwt = servletRequest.getHeader("Authorization").substring(7);
        List<String> roles = jwtTokenService.extractAuthorities(jwt);
        return roleRepository.findByNameIn(roles)
                .stream().map(e-> {
                    RoleDTO roleDTO = new RoleDTO();
                    roleDTO.setRoleName(e.getName());
                    roleDTO.setPermissions(e.getPermissions().stream().map(Permission::getName).toList());
                    return roleDTO;
                }).toList();


    }

    @Override
    @Transactional
    public void createUser(UserInfoDTO userInfo) {
        Role role = roleRepository.findByName(userInfo.getRole())
                .orElseThrow();
        User user = new User();
        user.setRoles(Set.of(role));
        user.setUsername(userInfo.getUserName());
        user.setPassword(encoder.encode(userInfo.getPassword()));
        user.setPhone(userInfo.getPhone());
        user.setEmail(userInfo.getEmail());
        user.setCreatedAt(LocalDateTime.now());
        if(role.getName().equalsIgnoreCase("DOCTOR")) {
            Doctor doctor = new Doctor();
            doctor.setName(userInfo.getFullName());
            doctor.setEmail(userInfo.getEmail());
            doctor.setPhone(userInfo.getPhone());
            doctor.setIsActive(false);
            user.setIsActive(false);
            doctor.setUser(user);
            user.setDoctor(doctor);
        }
        else {
            UserProfile userProfile = new UserProfile();
            userProfile.setName(userInfo.getFullName());
            userProfile.setCreatedAt(LocalDateTime.now());
            userProfile.setCreatedBy(1);
            user.setIsActive(role.getName().equalsIgnoreCase("USER"));
            userProfile.setUser(user);
            user.setProfile(userProfile);
        }

        userRepository.save(user);
    }

    @Override
    public UserInfoDTO getUserInfo(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException(messageResolver.getMessage("user.not.found")));
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setFullName(user.getUsername());
        userInfoDTO.setUserName(user.getUsername());
        userInfoDTO.setPassword(null);
        userInfoDTO.setEmail(user.getEmail());
        userInfoDTO.setPhone(user.getPhone());
        userInfoDTO.setRole(user.getRoles().stream().map(Role::getName).collect(Collectors.joining(",")));
        return userInfoDTO;


    }

    @Override
    public void addRoleToUser(Long userId, String roleName) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException(messageResolver.getMessage("user.not.found")));
        Role role = roleRepository.findByName(roleName)
                        .orElseThrow(()-> new ResourceNotFoundException(messageResolver.getMessage("role.not.found")));
        user.setIsActive(true);
        user.addRole(role);
        userRepository.save(user);
    }

    @Override
    public void activateUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException(messageResolver.getMessage("user.not.found")));
        user.setIsActive(true);
        userRepository.save(user);
        eventListener.onApplicationEvent(new UserActivationEvent(user));
    }
}
