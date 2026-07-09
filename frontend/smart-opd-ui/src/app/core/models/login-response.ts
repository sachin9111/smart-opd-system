import { UserInfo } from "./user-info";

export interface LoginResponse {

  accessToken: string;

  refreshToken: string;

  tokenType: string;

  expiresIn: number;

  user: UserInfo;

}