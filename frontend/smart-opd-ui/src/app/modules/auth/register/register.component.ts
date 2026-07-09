import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

import { AuthService } from '../../../core/services/auth.service';
import { RegisterRequest } from '../../../core/models/register-request';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {

  registerForm!: FormGroup;
  loading = false;
  hidePassword = true;
  hideConfirmPassword = true;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private toastr: ToastrService,
    private router: Router
  ) {
    this.initializeForm();
  }

  private initializeForm(): void {

    this.registerForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      mobile: ['', [
        Validators.required,
        Validators.pattern(/^[6-9]\d{9}$/)
      ]],
      password: ['', [
        Validators.required,
        Validators.minLength(8)
      ]],
      confirmPassword: ['', Validators.required]
    });

  }

  register(): void {

    if (this.registerForm.invalid) {
      this.registerForm.markAllAsTouched();
      return;
    }

    if (this.f['password'].value !== this.f['confirmPassword'].value) {
      this.toastr.error(
        'Passwords do not match.',
        'Validation'
      );
      return;
    }

    this.loading = true;

    const request: RegisterRequest = {
      firstName: this.f['firstName'].value,
      lastName: this.f['lastName'].value,
      email: this.f['email'].value,
      mobile: this.f['mobile'].value,
      password: this.f['password'].value
    };

    this.authService.register(request).subscribe({

      next: (response) => {

        this.loading = false;

        this.toastr.success(
          response.message,
          'Registration Successful'
        );

        this.router.navigate(['/login']);

      },

      error: (error) => {

        this.loading = false;

        if (error.status === 409) {

          this.toastr.error(
            error.error.message,
            'Registration Failed'
          );

        } else if (error.status === 400) {

          this.toastr.error(
            'Please check the entered details.',
            'Validation'
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

  toggleConfirmPassword(): void {
    this.hideConfirmPassword = !this.hideConfirmPassword;
  }

  get f() {
    return this.registerForm.controls;
  }

}