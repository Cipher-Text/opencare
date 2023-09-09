package com.ciphertext.opencarebackend.repository;

import com.ciphertext.opencarebackend.model.entity.MedicalSpeciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sadman
 */
@Repository
public interface MedicalSpecialityRepository extends JpaRepository<MedicalSpeciality, Integer> {
}
