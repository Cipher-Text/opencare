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
@Table(name="doctor_workplaces")
public class DoctorWorkplace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Column(name = "doctor_position")
    private String doctorPosition;

    @Enumerated(EnumType.STRING)
    @Column(name = "teacher_position")
    private TeacherPosition teacherPosition;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "speciality_id")
    private MedicalSpeciality medicalSpeciality;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "institution_id")
    private Institution institution;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @Column(name="start_date")
    private LocalDate startDate;

    @Column(name="end_date")
    private LocalDate endDate;
}