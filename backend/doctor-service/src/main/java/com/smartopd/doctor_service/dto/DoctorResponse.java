package com.smartopd.doctor_service.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorResponse {

    private Long id;

    private Long userId;

    private String registrationNumber;

    private String specialization;

    private String qualification;

    private Integer experience;

    private String department;

    private BigDecimal consultationFee;

    private Boolean active;
}