import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

import { AuthService } from '../../../core/services/auth.service';
import { TokenService } from '../../../core/services/token.service';
import { LoginRequest } from '../../../core/models/login-request';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  loginForm!: FormGroup;
  hidePassword = true;
  loading = false;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private tokenService: TokenService,
    private toastr: ToastrService,
    private router: Router
  ) {
    this.initializeForm();
  }

  private initializeForm(): void {

    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(8)]],
      rememberMe: [false]
    });

  }

  login(): void {

    if (this.loginForm.invalid) {
      this.loginForm.markAllAsTouched();
      return;
    }

    this.loading = true;

    const request: LoginRequest = {
      username: this.f['username'].value,
      password: this.f['password'].value
    };

    this.authService.login(request).subscribe({

      next: (response) => {

        this.loading = false;

        this.tokenService.saveAccessToken(response.accessToken);
        this.tokenService.saveRefreshToken(response.refreshToken);

        this.toastr.success(
          'Welcome ' + response.user.firstName,
          'Login Successful'
        );

        this.router.navigate(['/dashboard']);

      },

      error: (error) => {

        this.loading = false;

        if (error.status === 401) {

          this.toastr.error(
            'Invalid username or password.',
            'Login Failed'
          );

        } else {

          this.toastr.error(
            'Something went wrong.',
            'Error'
          );

        }

      }

    });

  }

  togglePassword(): void {
    this.hidePassword = !this.hidePassword;
  }

  get f() {
    return this.loginForm.controls;
  }

}