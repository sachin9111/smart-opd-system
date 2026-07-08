package com.smartopd.auth_service.service.interfaces;

import com.smartopd.auth_service.dto.request.RegisterRequest;
import com.smartopd.auth_service.dto.response.RegisterResponse;

public interface AuthService {

    RegisterResponse register(RegisterRequest request);

}
