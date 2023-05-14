package com.ciphertext.medicalinformationbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


/**
 * @author Sadman
 */
@Getter
@Setter
@Entity
@Table(name="doctor-workplaces")
public class DoctorWorkplace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Column(name = "position")
    private String position;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "speciality_id")
    private Speciality speciality;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "institution_id")
    private Institution institution;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;
}
