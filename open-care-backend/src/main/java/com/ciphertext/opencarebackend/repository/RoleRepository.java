package com.ciphertext.opencarebackend.repository;

import com.ciphertext.opencarebackend.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}