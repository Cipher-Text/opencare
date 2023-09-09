package com.ciphertext.opencarebackend.repository;

import com.ciphertext.opencarebackend.model.entity.MedicalTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalTestRepository extends JpaRepository<MedicalTest, Integer> {
}