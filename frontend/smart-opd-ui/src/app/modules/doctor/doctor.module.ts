import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DoctorListComponent } from './components/doctor-list/doctor-list.component';
import { DoctorAddComponent } from './components/doctor-add/doctor-add.component';
import { DoctorEditComponent } from './components/doctor-edit/doctor-edit.component';
import { DoctorViewComponent } from './components/doctor-view/doctor-view.component';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    DoctorListComponent,
    DoctorAddComponent,
    DoctorEditComponent,
    DoctorViewComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    ReactiveFormsModule
  ]
})
export class DoctorModule { }