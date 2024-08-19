package com.ciphertext.opencarebackend.model.entity;

import com.ciphertext.opencarebackend.model.enums.HospitalType;
import com.ciphertext.opencarebackend.model.enums.OrganizationType;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

/**
 * @author Sadman
 */
@Getter
@Setter
@Entity
@Table(name="institution")
public class Institution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="acronym")
    private String acronym;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="bn_name", nullable = false)
    private String bnName;

    @Column(name="established_year")
    private Integer establishedYear;

    @Column(name="enroll")
    private Integer enroll;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "district_id")
    private District district;

    @Enumerated(EnumType.STRING)
    @Column(name = "hospital_type", nullable = false)
    private HospitalType hospitalType;

    @Enumerated(EnumType.STRING)
    @Column(name = "organization_type", nullable = false)
    private OrganizationType organizationType;

    @Column(name="lat", nullable = false)
    private String lat;

    @Column(name="lon", nullable = false)
    private String lon;

    @Column(name="url", nullable = false)
    private String url;
}
