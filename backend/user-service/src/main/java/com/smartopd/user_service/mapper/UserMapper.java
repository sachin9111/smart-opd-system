package com.smartopd.user_service.mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.smartopd.user_service.entity.User;
import com.smartopd.user_service.event.UserCreatedEvent;

public class UserMapper {

    public static User toEntity(UserCreatedEvent event) {

        return User.builder()
                .authUserId(event.getAuthUserId())
                .firstName(event.getFirstName())
                .lastName(event.getLastName())
                .email(event.getEmail())
                .mobile(event.getMobile())
                .gender(event.getGender())
                .dob(event.getDob() != null
                        ? LocalDate.parse(event.getDob())
                        : null)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}