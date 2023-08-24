package com.ciphertext.opencarebackend.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "medical_test")
public class MedicalTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "parent_id")
    private Integer parentId;

    @Size(max = 100)
    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Size(max = 100)
    @NotNull
    @Column(name = "bn_name", nullable = false, length = 100)
    private String bnName;

    @NotNull
    @Column(name = "alternative_names", nullable = false)
    @Type(value = com.ciphertext.opencarebackend.model.util.CustomStringArrayType.class)
    private List<String> alternativeNames;

    @Size(max = 500)
    @Column(name = "description", length = 500)
    private String description;

}