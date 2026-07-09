package com.smartopd.auth_service.service.impl;



import java.time.LocalDateTime;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartopd.auth_service.dto.request.ChangePasswordRequest;
import com.smartopd.auth_service.dto.request.ForgotPasswordRequest;
import com.smartopd.auth_service.dto.request.LoginRequest;
import com.smartopd.auth_service.dto.request.RegisterRequest;
import com.smartopd.auth_service.dto.request.ResetPasswordRequest;
import com.smartopd.auth_service.dto.request.VerifyOtpRequest;
import com.smartopd.auth_service.dto.response.LoginResponse;
import com.smartopd.auth_service.dto.response.LogoutResponse;
import com.smartopd.auth_service.dto.response.MessageResponse;
import com.smartopd.auth_service.dto.response.RegisterResponse;
import com.smartopd.auth_service.dto.response.UserInfoResponse;
import com.smartopd.auth_service.entity.PasswordResetOtp;
import com.smartopd.auth_service.entity.RefreshToken;
import com.smartopd.auth_service.entity.Role;
import com.smartopd.auth_service.entity.User;
import com.smartopd.auth_service.entity.UserRole;
import com.smartopd.auth_service.entity.enums.RoleCode;
import com.smartopd.auth_service.entity.enums.UserStatus;
import com.smartopd.auth_service.exception.EmailAlreadyExistsException;
import com.smartopd.auth_service.exception.MobileAlreadyExistsException;
import com.smartopd.auth_service.exception.RoleNotFoundException;
import com.smartopd.auth_service.repository.PasswordResetOtpRepository;
import com.smartopd.auth_service.repository.RefreshTokenRepository;
import com.smartopd.auth_service.repository.RoleRepository;
import com.smartopd.auth_service.repository.UserRepository;
import com.smartopd.auth_service.repository.UserRoleRepository;
import com.smartopd.auth_service.security.JwtService;
import com.smartopd.auth_service.service.interfaces.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.smartopd.auth_service.dto.response.LogoutResponse;
import com.smartopd.auth_service.security.CustomUserDetails;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final UserRoleRepository userRoleRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	private final RefreshTokenRepository refreshTokenRepository;
	
	private final PasswordResetOtpRepository passwordResetOtpRepository;

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

    @Override
    @Transactional
    public LoginResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));

        User user = userRepository
                .findByEmailOrMobile(
                        request.getUsername(),
                        request.getUsername())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if (!user.isEnabled()) {
            throw new RuntimeException("User account is disabled.");
        }

        if (user.getStatus() != UserStatus.ACTIVE) {
            throw new RuntimeException("User account is not active.");
        }

        String accessToken = jwtService.generateAccessToken(user);

        String refreshToken = jwtService.generateRefreshToken(user.getEmail());

        refreshTokenRepository.deleteByUser(user);

        RefreshToken token = RefreshToken.builder()
                .token(refreshToken)
                .expiryDate(
                        LocalDateTime.now()
                                .plusSeconds(
                                        jwtService.getRefreshTokenExpirationSeconds()))
                .user(user)
                .build();

        refreshTokenRepository.save(token);

        user.setLastLogin(LocalDateTime.now());

        userRepository.save(user);

        UserInfoResponse userInfo = UserInfoResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .mobile(user.getMobile())
                .roles(
                        user.getUserRoles()
                                .stream()
                                .filter(UserRole::isActive)
                                .map(role ->
                                        role.getRole()
                                                .getCode()
                                                .name())
                                .toList())
                .build();

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiresIn(
                        jwtService.getAccessTokenExpirationSeconds())
                .user(userInfo)
                .build();

    }
    
    @Override
    @Transactional
    public LogoutResponse logout() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        User user = userDetails.getUser();

        refreshTokenRepository.deleteByUser(user);

        SecurityContextHolder.clearContext();

        return LogoutResponse.builder()
                .message("Logout successful.")
                .build();

    }
    
    
    @Override
    @Transactional(readOnly = true)
    public UserInfoResponse getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        User user = userDetails.getUser();

        return UserInfoResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .mobile(user.getMobile())
                .roles(
                        user.getUserRoles()
                                .stream()
                                .filter(UserRole::isActive)
                                .map(userRole ->
                                        userRole.getRole()
                                                .getCode()
                                                .name())
                                .collect(Collectors.toList()))
                .build();

    }
    
    
    @Override
    @Transactional
    public MessageResponse changePassword(ChangePasswordRequest request) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        User user = userDetails.getUser();

        if (!passwordEncoder.matches(
                request.getCurrentPassword(),
                user.getPassword())) {

            throw new RuntimeException("Current password is incorrect.");
        }

        if (request.getCurrentPassword()
                .equals(request.getNewPassword())) {

            throw new RuntimeException(
                    "New password must be different from current password.");
        }

        user.setPassword(
                passwordEncoder.encode(
                        request.getNewPassword()));

        userRepository.save(user);

        refreshTokenRepository.deleteByUser(user);

        SecurityContextHolder.clearContext();

        return MessageResponse.builder()
                .message("Password changed successfully. Please login again.")
                .build();

    }
    
    private String generateOtp(){

        return String.valueOf(
                ThreadLocalRandom.current()
                        .nextInt(100000,999999));

    }
    
    
    @Override
    @Transactional
    public MessageResponse forgotPassword(ForgotPasswordRequest request){

        User user=userRepository.findByEmail(request.getEmail())
                .orElseThrow(()->
                        new RuntimeException("User not found."));

        passwordResetOtpRepository.deleteByEmail(user.getEmail());

        String otp=generateOtp();

        PasswordResetOtp entity=
                PasswordResetOtp.builder()
                        .email(user.getEmail())
                        .otp(otp)
                        .expiryTime(
                                LocalDateTime.now().plusMinutes(10))
                        .build();

        passwordResetOtpRepository.save(entity);

        System.out.println("OTP : "+otp);

        return MessageResponse.builder()
                .message("OTP sent successfully.")
                .build();

    }
    
    
    @Override
    @Transactional
    public MessageResponse verifyOtp(VerifyOtpRequest request){

        PasswordResetOtp otp =
                passwordResetOtpRepository
                        .findTopByEmailOrderByIdDesc(request.getEmail())
                        .orElseThrow(() ->
                                new RuntimeException("OTP not found."));

        if(otp.isVerified()){
            throw new RuntimeException("OTP already verified.");
        }

        if(LocalDateTime.now().isAfter(otp.getExpiryTime())){
            throw new RuntimeException("OTP has expired.");
        }

        if(!otp.getOtp().equals(request.getOtp())){
            throw new RuntimeException("Invalid OTP.");
        }

        otp.setVerified(true);

        passwordResetOtpRepository.save(otp);

        return MessageResponse.builder()
                .message("OTP verified successfully.")
                .build();

    }
    
    @Override
    @Transactional
    public MessageResponse resetPassword(
            ResetPasswordRequest request){

        PasswordResetOtp otp =
                passwordResetOtpRepository
                        .findTopByEmailOrderByIdDesc(request.getEmail())
                        .orElseThrow(() ->
                                new RuntimeException("OTP not found."));

        if(!otp.isVerified()){
            throw new RuntimeException("OTP verification required.");
        }

        if(LocalDateTime.now().isAfter(otp.getExpiryTime())){
            throw new RuntimeException("OTP has expired.");
        }

        User user =
                userRepository.findByEmail(request.getEmail())
                        .orElseThrow(() ->
                                new RuntimeException("User not found."));

        user.setPassword(
                passwordEncoder.encode(
                        request.getNewPassword()));

        userRepository.save(user);

        passwordResetOtpRepository.deleteByEmail(
                request.getEmail());

        refreshTokenRepository.deleteByUser(user);

        return MessageResponse.builder()
                .message("Password reset successfully.")
                .build();

    }

}
