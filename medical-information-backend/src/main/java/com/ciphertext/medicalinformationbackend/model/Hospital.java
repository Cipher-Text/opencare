package com.ciphertext.medicalinformationbackend.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;


/**
 * @author Sadman
 */
@Getter
@Setter
@Entity
@Table(name="hospitals")
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "district_id")
    private District district;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "type_id")
    private Type type;

    @Column(name="number_of_bed")
    private int numberOfBed;
}
