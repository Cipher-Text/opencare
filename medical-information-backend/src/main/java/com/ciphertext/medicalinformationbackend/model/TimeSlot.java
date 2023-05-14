package com.ciphertext.medicalinformationbackend.model;

import com.ciphertext.medicalinformationbackend.enums.DaysOfWeek;
import com.ciphertext.medicalinformationbackend.enums.DegreeType;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Date;

/**
 * @author Sadman
 */
@Getter
@Setter
@Entity
@Table(name="time_slot")
public class TimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "doctor_workplace_id")
    private DoctorWorkplace doctorWorkplace;

    @Enumerated(EnumType.STRING)
    @Column(name = "degree_type")
    private DaysOfWeek daysOfWeek;

    @Column(name = "start_datetime")
    private Date startDateTime;

    @Column(name = "end_datetime")
    private Date endDateTime;
}
