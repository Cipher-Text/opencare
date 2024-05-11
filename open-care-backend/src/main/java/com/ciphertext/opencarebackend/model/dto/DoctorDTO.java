package com.ciphertext.opencarebackend.model.dto;

import com.ciphertext.opencarebackend.model.entity.User;
import com.ciphertext.opencarebackend.model.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author Sadman
 */
@Getter
@Setter
public class DoctorDTO {
    private Long id;
    private String name;
    private String bnName;
    private String gender;
    private String bmdcNo;
    private String phone;
    private String email;
    private String address;
    private int yearOfExperience;
    private String specialities;
    private String degrees;
    private String description;
    private byte[] image;
    private LocalDate startDate;
    private Boolean isActive;
    private Long userId;

    public int getYearOfExperience() {
        return (LocalDate.now().getYear() - startDate.getYear()) < 1 ?
                0 : (LocalDate.now().getYear() - startDate.getYear());
    }
}
