package com.ciphertext.opencarebackend.repository;

import com.ciphertext.opencarebackend.model.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    Optional<Rating> findByUser_IdAndDoctor_Id(long userId, long doctorId);
    Optional<Rating> findByUser_IdAndHospital_Id(long userId, int hospitalId);
    List<Rating> findAllByDoctor_Id(long doctorId);
    List<Rating> findAllByHospital_Id(int hospitalId);

    List<Rating> findAllByUser_Id(long userId);
}