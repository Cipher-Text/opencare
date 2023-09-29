package com.ciphertext.opencarebackend.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 100)
    @NotNull
    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @Size(max = 100)
    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Size(max = 20)
    @Column(name = "phone", length = 20)
    private String phone;

    @Size(max = 100)
    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "user", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private UserProfile profile;

    @OneToOne(mappedBy = "user", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Doctor doctor;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    public void setRoles(Set<Role> roles) {
        if(this.roles==null) {
            this.roles = new HashSet<>();
        }
        this.roles.addAll(roles);
    }

    public void addRole(Role role) {
        if(this.roles==null) {
            this.roles = new HashSet<>();
        }
        this.roles.add(role);
    }

    public void removeRole(Role role) {
        if(this.roles==null) {
            this.roles = new HashSet<>();
        }
        this.roles.remove(role);
    }

}