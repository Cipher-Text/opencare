package com.ciphertext.opencarebackend.repository;

import com.ciphertext.opencarebackend.model.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {
}