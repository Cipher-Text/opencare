package com.ciphertext.opencarebackend.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "rating_options")
@Entity
public class RatingOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type_name")
    private String typeName;

    @Column(name = "source")
    private String source;

    @Column(name = "description")
    private String description;

    @Column(name = "description_bn")
    private String descriptionBn;

}
