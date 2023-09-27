package com.ciphertext.opencarebackend.repository;

import com.ciphertext.opencarebackend.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);

    List<Role> findByNameIn(List<String> roleNames);
}