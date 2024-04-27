package com.ciphertext.opencarebackend.model.dto.ratings;

import lombok.Data;

@Data
public class UserRatingViewerDTO {
    private int userid;
    private RatingViewerDTO rating;
}
