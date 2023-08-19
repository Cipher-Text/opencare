package com.ciphertext.opencarebackend.repository;

import com.ciphertext.opencarebackend.model.entity.DoctorDegree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sadman
 */
@Repository
public interface DoctorDegreeRepository extends JpaRepository<DoctorDegree, Long> {
}
