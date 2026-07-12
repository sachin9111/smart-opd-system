import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

import { ChangePasswordComponent } from './change-password.component';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [
    ChangePasswordComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule
  ],
  exports: [
    ChangePasswordComponent
  ]
})
export class ChangePasswordModule { }