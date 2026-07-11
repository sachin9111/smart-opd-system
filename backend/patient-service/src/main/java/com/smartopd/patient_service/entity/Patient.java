package com.smartopd.patient_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // User ID from User Service
    @Column(nullable = false, unique = true)
    private Long userId;

    @Column(length = 10)
    private String bloodGroup;

    private Double height;

    private Double weight;

    @Column(length = 1000)
    private String allergies;

    @Column(length = 2000)
    private String medicalHistory;

    private String emergencyContactName;

    @Column(length = 15)
    private String emergencyContactNumber;

    private String insuranceNumber;

    @Builder.Default
    private Boolean active = true;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}