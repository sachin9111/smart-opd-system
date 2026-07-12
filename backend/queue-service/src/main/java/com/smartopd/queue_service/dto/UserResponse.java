package com.smartopd.queue_service.dto;

import lombok.*;

import java.time.LocalDate;

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