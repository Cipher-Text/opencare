package com.ciphertext.opencarebackend.model.entity;

import com.ciphertext.opencarebackend.model.enums.DegreeType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="degree")
public class Degree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="abbreviation", nullable = false)
    private String abbreviation;

    @Enumerated(EnumType.STRING)
    @Column(name = "degree_type", nullable = false)
    private DegreeType degreeType;
}
