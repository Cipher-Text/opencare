package com.ciphertext.opencarebackend.repository;

import com.ciphertext.opencarebackend.model.entity.Degree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sadman
 */
@Repository
public interface DegreeRepository extends JpaRepository<Degree, Integer> {
}
