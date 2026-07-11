package com.smartopd.user_service.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileResponse {

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