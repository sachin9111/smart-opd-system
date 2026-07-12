import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

import { ForgotPasswordComponent } from './forgot-password.component';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [
    ForgotPasswordComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule
  ],
  exports: [
    ForgotPasswordComponent
  ]
})
export class ForgotPasswordModule { }