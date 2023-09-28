package com.ciphertext.opencarebackend.service.impl;

import com.ciphertext.opencarebackend.model.dto.RoleDTO;
import com.ciphertext.opencarebackend.model.dto.UserInfoDTO;
import com.ciphertext.opencarebackend.model.entity.Doctor;
import com.ciphertext.opencarebackend.model.entity.Permission;
import com.ciphertext.opencarebackend.model.entity.Role;
import com.ciphertext.opencarebackend.model.entity.User;
import com.ciphertext.opencarebackend.repository.DoctorRepository;
import com.ciphertext.opencarebackend.repository.RoleRepository;
import com.ciphertext.opencarebackend.repository.UserRepository;
import com.ciphertext.opencarebackend.security.jwt.JWTTokenService;
import com.ciphertext.opencarebackend.service.UserManagementService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserManagementServiceImpl implements UserManagementService {
    private final RoleRepository roleRepository;
    private final DoctorRepository doctorRepository;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final JWTTokenService jwtTokenService;
    private final HttpServletRequest servletRequest;

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
            doctorRepository.save(doctor);
            //super admin/admin will activate the doctor through verification
            user.setIsActive(false);
        }
        userRepository.save(user);
    }

    @Override
    public UserInfoDTO getUserInfo(String userId) {
        return null;
    }
}
