package com.ciphertext.opencarebackend.model.entity;

import com.ciphertext.opencarebackend.model.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * @author Sadman
 */
@Getter
@Setter
@Entity
@Table(name="doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="bn_name")
    private String bnName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name="bmdc_no")
    private String bmdcNo;

    @Column(name="phone")
    private String phone;

    @Column(name="email")
    private String email;

    @Column(name="address")
    private String address;

    @Column(name="start_date")
    private LocalDate startDate;

    @Column(name="description")
    private String description;

    @Column(name="photo")
    private byte[] image;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
