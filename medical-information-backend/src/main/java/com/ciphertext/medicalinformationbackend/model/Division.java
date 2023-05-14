package com.ciphertext.medicalinformationbackend.model;

import lombok.*;

import jakarta.persistence.*;

/**
 * @author Sadman
 */
@Getter
@Setter
@Entity
@Table(name="division")
public class Division {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="bn_name")
    private String bnName;

    @Column(name="url")
    private String url;
}
