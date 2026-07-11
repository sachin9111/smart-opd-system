package com.smartopd.patient_service.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDetailsResponse {

    private Long id;

    private Long userId;

    // From User Service
    private String firstName;

    private String lastName;

    private String email;

    private String mobile;

    // From Patient Service
    private String bloodGroup;

    private Double height;

    private Double weight;

    private String allergies;

    private String medicalHistory;

    private String emergencyContactName;

    private String emergencyContactNumber;

    private String insuranceNumber;

    private Boolean active;

}