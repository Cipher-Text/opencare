package com.ciphertext.opencarebackend.repository;

import com.ciphertext.opencarebackend.model.entity.Doctor;
import com.ciphertext.opencarebackend.model.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author Sadman
 */
@Repository
public interface DoctorRepository  extends JpaRepository<Doctor, Long>, JpaSpecificationExecutor<Doctor> {
}
