package com.ciphertext.opencarebackend.model;

import com.ciphertext.opencarebackend.enums.HospitalType;
import com.ciphertext.opencarebackend.enums.OrganizationType;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;


/**
 * @author Sadman
 */
@Getter
@Setter
@Entity
@Table(name="hospital")
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="bn_name")
    private String bnName;

    @Column(name="number_of_bed", nullable = false)
    private Integer numberOfBed;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "district_id", nullable = false)
    private District district;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "upazila_id")
    private Upazila upazila;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "union_id")
    private Union union;

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
