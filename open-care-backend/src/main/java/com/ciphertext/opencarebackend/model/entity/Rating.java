package com.ciphertext.opencarebackend.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Size(max = 250)
    @Column(name = "comments", length = 250)
    private String comments;

    @Column(name = "clear_explanation")
    private Integer clearExplanation;

    @Column(name = "time_for_patients")
    private Integer timeForPatients;

    @Column(name = "attentive_listen")
    private Integer attentiveListen;

    @Column(name = "friendly_behavior")
    private Integer friendlyBehavior;

    @Column(name = "cleanliness")
    private Integer cleanliness;

    @Column(name = "stuff_behavior")
    private Integer stuffBehavior;

    @Column(name = "other_facilities")
    private Integer otherFacilities;

    @Column(name = "medical_test_facilities")
    private Integer medicalTestFacilities;

}