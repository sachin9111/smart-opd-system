package com.smartopd.doctor_service.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateDoctorRequest {

    @NotNull
    private Long userId;

    @NotBlank
    private String registrationNumber;

    @NotBlank
    private String specialization;

    private String qualification;

    @Min(0)
    private Integer experience;

    private String department;

    @DecimalMin("0.0")
    private BigDecimal consultationFee;
}