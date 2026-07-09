package com.smartopd.user_service.event;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreatedEvent {

    private Long authUserId;

    private String firstName;

    private String lastName;

    private String email;

    private String mobile;

    private String gender;

    private String dob;
}