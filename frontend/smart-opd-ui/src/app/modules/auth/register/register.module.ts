import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

import { RegisterComponent } from './register.component';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [
    RegisterComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule   
  ],
  exports: [
    RegisterComponent
  ]
})
export class RegisterModule { }