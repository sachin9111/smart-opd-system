package com.smartopd.auth_service.service.impl;



import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartopd.auth_service.dto.request.RegisterRequest;
import com.smartopd.auth_service.dto.response.RegisterResponse;
import com.smartopd.auth_service.entity.Role;
import com.smartopd.auth_service.entity.User;
import com.smartopd.auth_service.entity.UserRole;
import com.smartopd.auth_service.entity.enums.RoleCode;
import com.smartopd.auth_service.entity.enums.UserStatus;
import com.smartopd.auth_service.exception.EmailAlreadyExistsException;
import com.smartopd.auth_service.exception.MobileAlreadyExistsException;
import com.smartopd.auth_service.exception.RoleNotFoundException;
import com.smartopd.auth_service.repository.RoleRepository;
import com.smartopd.auth_service.repository.UserRepository;
import com.smartopd.auth_service.repository.UserRoleRepository;
import com.smartopd.auth_service.service.interfaces.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final UserRoleRepository userRoleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public RegisterResponse register(RegisterRequest request) {

        // Check duplicate email
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists.");
        }

        // Check duplicate mobile
        if (userRepository.existsByMobile(request.getMobile())) {
            throw new MobileAlreadyExistsException("Mobile number already exists.");
        }

        // Get default role
        Role role = roleRepository.findByCode(RoleCode.ROLE_PATIENT)
                .orElseThrow(() ->
                        new RoleNotFoundException("ROLE_PATIENT not found."));

        // Create User
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .mobile(request.getMobile())
                .password(passwordEncoder.encode(request.getPassword()))
                .status(UserStatus.ACTIVE)
                .enabled(true)
                .emailVerified(false)
                .mobileVerified(false)
                .build();

        user = userRepository.save(user);

        // Assign Role
        UserRole userRole = UserRole.builder()
                .user(user)
                .role(role)
                .active(true)
                .build();

        userRoleRepository.save(userRole);

        return RegisterResponse.builder()
                .userId(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .mobile(user.getMobile())
                .role(role.getCode().name())
                .registeredAt(user.getCreatedAt())
                .message("User registered successfully.")
                .build();

    }

}
