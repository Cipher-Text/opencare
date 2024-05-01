package com.ciphertext.opencarebackend.model.dto.ratings;

import lombok.Data;

@Data
public class RatingOptionDTO {
    private String typeName;
    private String source;
    private String description;
    private String descriptionBn;
    private int rating;

}


