package com.ciphertext.opencarebackend.model.entity;

import com.ciphertext.opencarebackend.model.enums.DaysOfWeek;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;


/**
 * @author Sadman
 */
@Getter
@Setter
@Entity
@Table(name="doctor_schedule")
public class DoctorSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "doctor_workplace_id")
    private DoctorWorkplace doctorWorkplace;

    @Enumerated(EnumType.STRING)
    @Column(name = "day", nullable = false)
    private DaysOfWeek daysOfWeek;

    @Column(name="start_time", nullable = false)
    private Time startTime;

    @Column(name="end_time", nullable = false)
    private Time endTime;
}
