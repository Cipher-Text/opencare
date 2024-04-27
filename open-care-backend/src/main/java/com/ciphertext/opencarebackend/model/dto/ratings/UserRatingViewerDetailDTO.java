package com.ciphertext.opencarebackend.model.dto.ratings;

import lombok.Data;

import java.util.List;

@Data
public class UserRatingViewerDetailDTO {
    private String receiverType;
    private String receiverId;
    private List<RatingOptionDTO> ratings;
    private String comments;
}
