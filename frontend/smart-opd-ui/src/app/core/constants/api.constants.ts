export class ApiConstants {

  private constructor() {}

  public static readonly BASE_URL =
    'http://localhost:8080/api/v1';

  public static readonly AUTH = {

    LOGIN: '/auth/login',

    REGISTER: '/auth/register',

    LOGOUT: '/auth/logout',

    PROFILE: '/auth/me',

    CHANGE_PASSWORD: '/auth/change-password',

    FORGOT_PASSWORD: '/auth/forgot-password',

    VERIFY_OTP: '/auth/verify-otp',

    RESET_PASSWORD: '/auth/reset-password' ,

    ME: '/auth/me',

    

  };

}