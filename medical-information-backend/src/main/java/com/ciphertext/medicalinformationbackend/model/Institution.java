package com.ciphertext.medicalinformationbackend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Sadman
 */
@Getter
@Setter
@Entity
@Table(name="institutions")
public class Institution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "district_id")
    private District district;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "type_id")
    private Type type;

    @Column(name="acronym")
    private String acronym;

    @Column(name="university")
    private String university;

    @Column(name="established")
    private int established;

    @Column(name="started")
    private int started;

    @Column(name="enroll")
    private int enroll;

    @Column(name="url")
    private String url;
}
