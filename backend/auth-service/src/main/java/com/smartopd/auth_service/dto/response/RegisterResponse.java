package com.smartopd.auth_service.dto.response;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterResponse {

    private Long userId;

    private String firstName;

    private String lastName;

    private String email;

    private String mobile;

    private String role;

    private String message;

    private LocalDateTime registeredAt;

}
