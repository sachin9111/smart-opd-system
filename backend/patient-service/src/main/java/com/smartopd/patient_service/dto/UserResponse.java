package com.smartopd.patient_service.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private Long id;

    private Long authUserId;

    private String firstName;

    private String lastName;

    private String gender;

    private LocalDate dob;

    private String mobile;

    private String email;

    private String profilePhoto;

}
