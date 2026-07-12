import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

import { ResetPasswordComponent } from './reset-password.component';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [
    ResetPasswordComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule
  ],
  exports: [
    ResetPasswordComponent
  ]
})
export class ResetPasswordModule { }