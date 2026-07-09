import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

import { AuthService } from '../../../core/services/auth.service';
import { ResetPasswordRequest } from '../../../core/models/reset-password-request';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.scss']
})
export class ResetPasswordComponent {

  loading = false;
  hidePassword = true;
  hideConfirmPassword = true;

  resetForm = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(8)]],
    confirmPassword: ['', Validators.required]
  });

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private toastr: ToastrService,
    private router: Router
  ) {
  }

  resetPassword(): void {

    if (this.resetForm.invalid) {
      this.resetForm.markAllAsTouched();
      return;
    }

    if (this.f.password.value !== this.f.confirmPassword.value) {
      this.toastr.error('Passwords do not match.');
      return;
    }

    this.loading = true;

    const request: ResetPasswordRequest = {
      email: this.f.email.value!,
      password: this.f.password.value!
    };

    this.authService.resetPassword(request).subscribe({

      next: () => {

        this.loading = false;

        this.toastr.success(
          'Password changed successfully.'
        );

        this.router.navigate(['/login']);

      },

      error: () => {

        this.loading = false;

        this.toastr.error(
          'Unable to reset password.'
        );

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
    return this.resetForm.controls;
  }

}