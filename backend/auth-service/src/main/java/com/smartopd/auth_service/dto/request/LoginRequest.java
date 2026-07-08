package com.smartopd.auth_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotBlank(message = "Email or mobile number is required.")
    private String username;

    @NotBlank(message = "Password is required.")
    @Size(min = 8, max = 20,
            message = "Password must be between 8 and 20 characters.")
    private String password;

}