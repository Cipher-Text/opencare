package com.ciphertext.opencarebackend.repository;

import com.ciphertext.opencarebackend.model.entity.DoctorSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sadman
 */
@Repository
public interface DoctorScheduleRepository extends JpaRepository<DoctorSchedule, Long> {
}
