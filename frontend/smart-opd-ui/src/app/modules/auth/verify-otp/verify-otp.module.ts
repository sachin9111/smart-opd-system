import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

import { VerifyOtpComponent } from './verify-otp.component';

@NgModule({
  declarations: [
    VerifyOtpComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  exports: [
    VerifyOtpComponent
  ]
})
export class VerifyOtpModule { }