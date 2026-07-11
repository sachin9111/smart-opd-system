package com.smartopd.patient_service.dto;

import lombok.Data;

@Data
public class CreatePatientRequest {

    private Long userId;

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