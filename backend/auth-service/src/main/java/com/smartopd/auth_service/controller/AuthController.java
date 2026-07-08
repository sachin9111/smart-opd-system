package com.smartopd.auth_service.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.smartopd.auth_service.dto.request.RegisterRequest;
import com.smartopd.auth_service.dto.response.ApiResponse;
import com.smartopd.auth_service.dto.response.RegisterResponse;
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

}
