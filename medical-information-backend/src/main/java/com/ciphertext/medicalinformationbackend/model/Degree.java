package com.ciphertext.medicalinformationbackend.model;

import com.ciphertext.medicalinformationbackend.enums.DegreeType;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

/**
 * @author Sadman
 */
@Getter
@Setter
@Entity
@Table(name="degree")
public class Degree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="abbreviation")
    private String abbreviation;

    @Enumerated(EnumType.STRING)
    @Column(name = "degree_type")
    private DegreeType degreeType;
}
