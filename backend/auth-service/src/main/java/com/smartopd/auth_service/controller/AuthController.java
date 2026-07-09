package com.smartopd.auth_service.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.smartopd.auth_service.dto.request.ChangePasswordRequest;
import com.smartopd.auth_service.dto.request.ForgotPasswordRequest;
import com.smartopd.auth_service.dto.request.LoginRequest;
import com.smartopd.auth_service.dto.request.RegisterRequest;
import com.smartopd.auth_service.dto.request.ResetPasswordRequest;
import com.smartopd.auth_service.dto.request.VerifyOtpRequest;
import com.smartopd.auth_service.dto.response.ApiResponse;
import com.smartopd.auth_service.dto.response.LoginResponse;
import com.smartopd.auth_service.dto.response.LogoutResponse;
import com.smartopd.auth_service.dto.response.MessageResponse;
import com.smartopd.auth_service.dto.response.RegisterResponse;
import com.smartopd.auth_service.dto.response.UserInfoResponse;
import com.smartopd.auth_service.service.interfaces.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegisterResponse>> register(
            @Valid @RequestBody RegisterRequest request) {

    	RegisterResponse response = authService.register(request);

    	ApiResponse<RegisterResponse> apiResponse =
    	        ApiResponse.<RegisterResponse>builder()
    	                .success(true)
    	                .status(HttpStatus.CREATED.value())
    	                .message("User registered successfully.")
    	                .data(response)
    	                .build();

    	return ResponseEntity.status(HttpStatus.CREATED)
    	        .body(apiResponse);
    }
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {

        LoginResponse response = authService.login(request);

        return ResponseEntity.ok(response);

    }
    
    @PostMapping("/logout")
    public ResponseEntity<LogoutResponse> logout() {

        return ResponseEntity.ok(authService.logout());

    }
    
    @GetMapping("/me")
    public ResponseEntity<UserInfoResponse> getCurrentUser() {

        return ResponseEntity.ok(
                authService.getCurrentUser());

    }
    
    @PostMapping("/change-password")
    public ResponseEntity<MessageResponse> changePassword(
            @Valid @RequestBody ChangePasswordRequest request) {

        return ResponseEntity.ok(
                authService.changePassword(request));

    }
    
    @PostMapping("/forgot-password")
    public ResponseEntity<MessageResponse> forgotPassword(
            @Valid @RequestBody ForgotPasswordRequest request){

        return ResponseEntity.ok(
                authService.forgotPassword(request));

    }
    
    @PostMapping("/verify-otp")
    public ResponseEntity<MessageResponse> verifyOtp(
            @Valid @RequestBody VerifyOtpRequest request){

        return ResponseEntity.ok(
                authService.verifyOtp(request));

    }
    
    @PostMapping("/reset-password")
    public ResponseEntity<MessageResponse> resetPassword(
            @Valid @RequestBody ResetPasswordRequest request){

        return ResponseEntity.ok(
                authService.resetPassword(request));

    }

}
