import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ApiConstants } from '../constants/api.constants';

import { LoginRequest } from '../models/login-request';
import { LoginResponse } from '../models/login-response';

import { RegisterRequest } from '../models/register-request';
import { RegisterResponse } from '../models/register-response';
import { ForgotPasswordRequest } from '../models/forgot-password-request';
import { VerifyOtpRequest } from '../models/verify-otp-request';
import { ResetPasswordRequest } from '../models/reset-password-request';
import { UserInfo } from '../models/user-info';
import { ChangePasswordRequest } from '../models/change-password-request';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private http: HttpClient
  ) { }

  login(request: LoginRequest): Observable<LoginResponse> {

    return this.http.post<LoginResponse>(
      ApiConstants.BASE_URL + ApiConstants.AUTH.LOGIN,
      request
    );

  }

  register(request: RegisterRequest): Observable<RegisterResponse> {

    return this.http.post<RegisterResponse>(
      ApiConstants.BASE_URL + ApiConstants.AUTH.REGISTER,
      request
    );

  }

  logout(): Observable<any> {

    return this.http.post(
      ApiConstants.BASE_URL + ApiConstants.AUTH.LOGOUT,
      {}
    );

  }

  getCurrentUser(): Observable<any> {

    return this.http.get(
      ApiConstants.BASE_URL + ApiConstants.AUTH.PROFILE
    );

  }

  forgotPassword(request: ForgotPasswordRequest) {

    return this.http.post(
        ApiConstants.BASE_URL + ApiConstants.AUTH.FORGOT_PASSWORD,
        request
    );

  }

  verifyOtp(request: VerifyOtpRequest) {
    return this.http.post(
      ApiConstants.BASE_URL + ApiConstants.AUTH.VERIFY_OTP,
      request
    );
  }

  resetPassword(request: ResetPasswordRequest) {

    return this.http.post(
      ApiConstants.BASE_URL + ApiConstants.AUTH.RESET_PASSWORD,
      request
    );

  }

  getProfile() {

    return this.http.get<UserInfo>(
      ApiConstants.BASE_URL + ApiConstants.AUTH.ME
    );

  }

  changePassword(request:ChangePasswordRequest){

    return this.http.post(
        ApiConstants.BASE_URL +
        ApiConstants.AUTH.CHANGE_PASSWORD,
        request
    );

}



}