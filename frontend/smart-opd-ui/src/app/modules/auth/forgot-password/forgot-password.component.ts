import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

import { AuthService } from '../../../core/services/auth.service';
import { ForgotPasswordRequest } from '../../../core/models/forgot-password-request';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.scss']
})
export class ForgotPasswordComponent {

  loading = false;

  forgotForm = this.fb.group({
    email: [
      '',
      [
        Validators.required,
        Validators.email
      ]
    ]
  });

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private toastr: ToastrService,
    private router: Router
  ) {
  }

  sendOtp(): void {

    if (this.forgotForm.invalid) {
      this.forgotForm.markAllAsTouched();
      return;
    }

    this.loading = true;

    const request: ForgotPasswordRequest = {
      email: this.f.email.value!
    };

    this.authService.forgotPassword(request).subscribe({

      next: () => {

        this.loading = false;

        this.toastr.success(
          'OTP has been sent to your email.',
          'Success'
        );

        this.router.navigate(['/login']);

      },

      error: () => {

        this.loading = false;

        this.toastr.error(
          'Unable to process request.',
          'Error'
        );

      }

    });

  }

  get f() {
    return this.forgotForm.controls;
  }

}