export class ApiConstants {

  private constructor() {}

  public static readonly BASE_URL = 'http://localhost:8080/api/v1';
  public static readonly BASE_URL_GATEWAY = 'http://localhost:8081/api/v1';

  public static readonly PATIENT = {

        LIST: '/patients',

    }

 public static readonly  USER = {

    LIST: '/users'

}


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

  public static readonly DOCTOR = {

    GET_ALL: '/doctors',

    GET_BY_ID: '/doctors',

    CREATE: '/api/v1/doctors',

    UPDATE: '/api/v1/doctors',

    DELETE: '/api/v1/doctors'

  }

}