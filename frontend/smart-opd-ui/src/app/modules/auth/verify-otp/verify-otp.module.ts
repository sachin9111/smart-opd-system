import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

import { VerifyOtpComponent } from './verify-otp.component';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [
    VerifyOtpComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule
  ],
  exports: [
    VerifyOtpComponent
  ]
})
export class VerifyOtpModule { }