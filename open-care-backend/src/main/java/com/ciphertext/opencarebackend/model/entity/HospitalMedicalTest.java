package com.ciphertext.opencarebackend.model.entity;

import com.ciphertext.opencarebackend.model.enums.TeacherPosition;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


/**
 * @author Sadman
 */
@Getter
@Setter
@Entity
@Table(name="hospital_medical_test")
public class HospitalMedicalTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "medical_test_id")
    private MedicalTest medicalTest;

    @Column(name = "price")
    private Integer price;

    @Column(name = "is_active")
    private Boolean isActive;
}