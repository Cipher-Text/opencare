package com.ciphertext.opencarebackend.service;

import com.ciphertext.opencarebackend.model.dto.RoleDTO;
import com.ciphertext.opencarebackend.model.dto.UserInfoDTO;

import java.util.List;

public interface UserManagementService {

    List<RoleDTO> getUserRole();

    void createUser(UserInfoDTO userInfo);

    UserInfoDTO getUserInfo(Long userId);

    void addRoleToUser(Long userId, String roleName);

    void activateUser(Long userId);
}
