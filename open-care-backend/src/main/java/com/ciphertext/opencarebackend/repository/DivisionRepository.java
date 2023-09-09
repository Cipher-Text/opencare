package com.ciphertext.opencarebackend.repository;

import com.ciphertext.opencarebackend.model.entity.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sadman
 */
@Repository
public interface DivisionRepository extends JpaRepository<Division, Integer> {
}
