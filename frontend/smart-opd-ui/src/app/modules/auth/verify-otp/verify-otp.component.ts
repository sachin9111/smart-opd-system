import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

import { AuthService } from '../../../core/services/auth.service';
import { VerifyOtpRequest } from '../../../core/models/verify-otp-request';

@Component({
  selector: 'app-verify-otp',
  templateUrl: './verify-otp.component.html',
  styleUrls: ['./verify-otp.component.scss']
})
export class VerifyOtpComponent {

  loading = false;

  verifyForm = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    otp: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(6)]]
  });

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private toastr: ToastrService,
    private router: Router
  ) { }

  verifyOtp(): void {

    if (this.verifyForm.invalid) {
      this.verifyForm.markAllAsTouched();
      return;
    }

    this.loading = true;

    const request: VerifyOtpRequest = {
      email: this.verifyForm.value.email!,
      otp: this.verifyForm.value.otp!
    };

    this.authService.verifyOtp(request).subscribe({

      next: () => {

        this.loading = false;

        this.toastr.success('OTP verified successfully.');

        this.router.navigate(['/reset-password']);

      },

      error: () => {

        this.loading = false;

        this.toastr.error('Invalid or expired OTP.');

      }

    });

  }

  get f() {
    return this.verifyForm.controls;
  }

}