import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  private readonly ACCESS_TOKEN = 'access_token';

  private readonly REFRESH_TOKEN = 'refresh_token';

  private readonly ROLES = 'roles';

  saveAccessToken(token: string): void {

    localStorage.setItem(this.ACCESS_TOKEN, token);

  }

  getAccessToken(): string | null {

    return localStorage.getItem(this.ACCESS_TOKEN);

  }

  saveRoles(roles:string[]){
     localStorage.setItem(this.ROLES, roles[0]);
  }

  getRoles() : string | null {
    return localStorage.getItem(this.ROLES);
  }

  

  saveRefreshToken(token: string): void {

    localStorage.setItem(this.REFRESH_TOKEN, token);

  }

  getRefreshToken(): string | null {

    return localStorage.getItem(this.REFRESH_TOKEN);

  }

  clear(): void {

    localStorage.removeItem(this.ACCESS_TOKEN);

    localStorage.removeItem(this.REFRESH_TOKEN);

  }

  isLoggedIn(): boolean {

    return this.getAccessToken() !== null;

  }

  logout(): void {

    this.clear();

  }

}