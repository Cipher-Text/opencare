package com.ciphertext.opencarebackend.model.dto.ratings;

import lombok.Data;

@Data
public class UserRatingWrapperDTO {
    private int userId;
    private String userName;
    private String userEmail;
    private RatingViewerDTO userRating;
}
