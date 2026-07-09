package com.smartopd.user_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long authUserId;

    @Column(nullable = false)
    private String firstName;

    private String lastName;

    private String gender;

    private LocalDate dob;

    @Column(length = 15)
    private String mobile;

    @Column(nullable = false, unique = true)
    private String email;

    private String profilePhoto;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}