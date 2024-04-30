package com.ciphertext.opencarebackend.model.dto.ratings;

import lombok.Data;

import java.util.List;

@Data
public class RatingDetailViewerDTO {
    private double overallRating;
    private List<UserRatingWrapperDTO> ratingDetails;
}
