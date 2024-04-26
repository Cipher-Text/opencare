package com.ciphertext.opencarebackend.service;

import com.ciphertext.opencarebackend.model.dto.ratings.RatingDetailViewerDTO;
import com.ciphertext.opencarebackend.model.dto.ratings.RatingViewerDTO;
import com.ciphertext.opencarebackend.model.dto.ratings.UserRatingViewerDTO;
import com.ciphertext.opencarebackend.model.dto.ratings.UserRatingViewerDetailDTO;

import java.util.List;

public interface RatingService {
    void addOrUpdateRatingToDoctor(UserRatingViewerDTO userRating, int DoctorId);
    void addOrUpdateRatingToHospital(UserRatingViewerDTO userRating, int hospitalId);
    List<UserRatingViewerDetailDTO> viewLoggedInUserRating();
    void updateLoggedInUserRating(RatingViewerDTO updatedValue);
    RatingDetailViewerDTO viewDoctorRatings(int doctorId);
    RatingDetailViewerDTO viewHospitalRatings(int hospitalId);
}
