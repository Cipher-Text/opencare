package com.ciphertext.opencarebackend.controller;

import com.ciphertext.opencarebackend.annotations.SecureAPI;
import com.ciphertext.opencarebackend.model.dto.ratings.RatingDetailViewerDTO;
import com.ciphertext.opencarebackend.model.dto.ratings.RatingViewerDTO;
import com.ciphertext.opencarebackend.model.dto.ratings.UserRatingViewerDTO;
import com.ciphertext.opencarebackend.model.dto.ratings.UserRatingViewerDetailDTO;
import com.ciphertext.opencarebackend.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
@RequiredArgsConstructor
@SecureAPI
public class RatingController {
    private final RatingService ratingService;

    //give/update a rating to doctor -> user operation
    @PostMapping("/doctor/{doctorId}")
    public ResponseEntity<?> addOrUpdateRatingToDoctor(@RequestBody UserRatingViewerDTO ratingGeneratorDTO, @PathVariable("doctorId") int doctorId) {
        ratingService.addOrUpdateRatingToDoctor(ratingGeneratorDTO,doctorId);
        return ResponseEntity.ok("");
    }
    //give/update a rating to hospital -> user operation
    @PostMapping("/hospital/{hospitalId}")
    public ResponseEntity<?> addOrUpdateRatingToHospital(@RequestBody UserRatingViewerDTO ratingGeneratorDTO, @PathVariable("hospitalId") int hospitalId) {
        ratingService.addOrUpdateRatingToHospital(ratingGeneratorDTO,hospitalId);
        return ResponseEntity.ok("");
    }
    //view a user rating -> user operation;
    @GetMapping("/user")
    public ResponseEntity<?> viewLoggedInUserRating() {
        List<UserRatingViewerDetailDTO> userRatings = ratingService.viewLoggedInUserRating();
        return ResponseEntity.ok(userRatings);
    }
    //update a user rating -> user operation;
    @PostMapping("/user")
    public ResponseEntity<?> updateLoggedInUserRating(@RequestBody RatingViewerDTO userRating) {
         ratingService.updateLoggedInUserRating(userRating);
         return ResponseEntity.ok("");
    }

    //view doctor details rating with calculated total rating -> any log in user can view (shall we make this for public?);
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<?> viewDoctorRatings(@PathVariable("doctorId") int doctorId) {
        RatingDetailViewerDTO doctorRatings = ratingService.viewDoctorRatings(doctorId);
        return ResponseEntity.ok(doctorRatings);
    }
    //view doctor details rating with calculated total rating -> any log in user can view (shall we make this for public?);
    @GetMapping("/hospital/{hospitalId}")
    public ResponseEntity<?> viewHospitalRatings(@PathVariable("hospitalId") int hospitalId) {
        RatingDetailViewerDTO hospitalRatings = ratingService.viewHospitalRatings(hospitalId);
        return ResponseEntity.ok(hospitalRatings);
    }

    //add new rating option -> super admin operation;

    //remove a rating option -> super admin operation

    //update a rating options description -> super admin operation;

}
