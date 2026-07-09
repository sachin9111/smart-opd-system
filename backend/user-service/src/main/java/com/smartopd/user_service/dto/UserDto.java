package com.smartopd.user_service.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserDto {

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