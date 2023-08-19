package com.ciphertext.opencarebackend.repository;

import com.ciphertext.opencarebackend.model.entity.DoctorWorkplace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sadman
 */
@Repository
public interface DoctorWorkplaceRepository extends JpaRepository<DoctorWorkplace, Long> {
}
