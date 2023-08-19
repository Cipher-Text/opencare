package com.ciphertext.opencarebackend.repository;

import com.ciphertext.opencarebackend.model.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sadman
 */
@Repository
public interface DoctorRepository  extends JpaRepository<Doctor, Long> {
}
