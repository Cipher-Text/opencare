package com.ciphertext.opencarebackend.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

/**
 * @author Sadman
 */
@Getter
@Setter
@Entity
@Table(name="district")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "division_id", nullable = false)
    private Division division;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="bn_name", nullable = false)
    private String bnName;

    @Column(name="lat")
    private String lat;

    @Column(name="lon")
    private String lon;

    @Column(name="url")
    private String url;
}
