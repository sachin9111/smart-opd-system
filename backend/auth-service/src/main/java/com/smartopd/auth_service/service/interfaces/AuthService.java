package com.smartopd.auth_service.service.interfaces;

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

public interface AuthService {

    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);
    
    LogoutResponse logout();
    
    UserInfoResponse getCurrentUser();
    
    MessageResponse changePassword(ChangePasswordRequest request);
    
    MessageResponse forgotPassword(ForgotPasswordRequest request);
    
    MessageResponse verifyOtp(VerifyOtpRequest request);
    
    MessageResponse resetPassword(ResetPasswordRequest request);

}