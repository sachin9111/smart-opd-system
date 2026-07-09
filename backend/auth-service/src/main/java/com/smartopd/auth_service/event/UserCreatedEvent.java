package com.smartopd.auth_service.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreatedEvent {

    private Long authUserId;

    private String firstName;

    private String lastName;

    private String email;

    private String mobile;

}