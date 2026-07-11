package com.smartopd.user_service.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.smartopd.user_service.dto.ProfileResponse;
import com.smartopd.user_service.dto.UserDto;
import com.smartopd.user_service.entity.User;
import com.smartopd.user_service.event.UserCreatedEvent;
import com.smartopd.user_service.repository.UserRepository;
import com.smartopd.user_service.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto getUserById(Long id) {

        User user = userRepository.findById(id).orElseThrow();

        return convertToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private UserDto convertToDto(User user) {

        UserDto dto = new UserDto();

        dto.setId(user.getId());
        dto.setAuthUserId(user.getAuthUserId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setGender(user.getGender());
        dto.setDob(user.getDob());
        dto.setMobile(user.getMobile());
        dto.setEmail(user.getEmail());
        dto.setProfilePhoto(user.getProfilePhoto());

        return dto;
    }
    
    @Override
    public void createUser(UserCreatedEvent event) {

        User user = User.builder()
                .authUserId(event.getAuthUserId())
                .firstName(event.getFirstName())
                .lastName(event.getLastName())
                .email(event.getEmail())
                .mobile(event.getMobile())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        
        if (userRepository.existsByAuthUserId(event.getAuthUserId())) {
            log.info("User already exists.");
            return;
        }

        userRepository.save(user);

        log.info("User Profile Created Successfully");
    }
    
    @Override
    public ProfileResponse getProfile(Long authUserId) {

        User user = userRepository.findByAuthUserId(authUserId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return ProfileResponse.builder()
                .id(user.getId())
                .authUserId(user.getAuthUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .gender(user.getGender())
                .dob(user.getDob())
                .mobile(user.getMobile())
                .email(user.getEmail())
                .profilePhoto(user.getProfilePhoto())
                .build();
    }
}