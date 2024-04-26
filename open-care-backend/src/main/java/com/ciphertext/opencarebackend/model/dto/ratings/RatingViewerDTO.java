package com.ciphertext.opencarebackend.model.dto.ratings;

import lombok.Data;

import java.util.List;

@Data
public class RatingViewerDTO {
    private List<RatingOptionDTO> ratings;
    private String comments;
}
