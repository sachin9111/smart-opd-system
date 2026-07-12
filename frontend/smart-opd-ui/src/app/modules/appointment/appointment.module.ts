import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AppointmentRoutingModule } from './appointment-routing.module';
import { AppointmentListComponent } from './components/appointment-list/appointment-list.component';
import { AppointmentEditComponent } from './components/appointment-edit/appointment-edit.component';
import { AppointmentAddComponent } from './components/appointment-add/appointment-add.component';
import { AppointmentViewComponent } from './components/appointment-view/appointment-view.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MyAppointmentsComponent } from './components/my-appointments/my-appointments.component';


@NgModule({
  declarations: [
    AppointmentListComponent,
    AppointmentEditComponent,
    AppointmentAddComponent,
    AppointmentViewComponent,
    MyAppointmentsComponent
  ],
  imports: [
    CommonModule,
    AppointmentRoutingModule,
    ReactiveFormsModule,
    FormsModule
  ]
})
export class AppointmentModule { }
