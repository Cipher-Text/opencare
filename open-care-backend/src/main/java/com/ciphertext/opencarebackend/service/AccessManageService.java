package com.ciphertext.opencarebackend.service;

import java.util.List;

public interface AccessManageService {

     List<String> getRolePermission(String roleName);
     List<String> getRolePermission();
     void addPermissionToRole(String permissionName,String roleName);

     void removePermissionFromRole(String permissionName,String roleName);


}
