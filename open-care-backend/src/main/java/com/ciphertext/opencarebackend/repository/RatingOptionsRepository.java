package com.ciphertext.opencarebackend.repository;

import com.ciphertext.opencarebackend.model.entity.RatingOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingOptionsRepository extends JpaRepository<RatingOption, Integer> {
    Optional<RatingOption> findByTypeName(String name);
}
