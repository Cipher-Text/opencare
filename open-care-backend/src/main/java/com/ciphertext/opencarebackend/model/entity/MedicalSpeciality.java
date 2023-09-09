package com.ciphertext.opencarebackend.model.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

/**
 * @author Sadman
 */
@Getter
@Setter
@Entity
@Table(name="medical_speciality")
public class MedicalSpeciality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "parent_id")
    private MedicalSpeciality parentMedicalSpeciality;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="bn_name", nullable = false)
    private String bnName;

    @Column(name="description")
    private String description;
}
