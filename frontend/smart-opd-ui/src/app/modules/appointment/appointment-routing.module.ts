import { NgModule } from '@angular/core';

import { RouterModule, Routes } from '@angular/router';

import { AppointmentListComponent } from './components/appointment-list/appointment-list.component';

import { AppointmentAddComponent } from './components/appointment-add/appointment-add.component';

import { AppointmentEditComponent } from './components/appointment-edit/appointment-edit.component';

import { AppointmentViewComponent } from './components/appointment-view/appointment-view.component';
import { MyAppointmentsComponent } from './components/my-appointments/my-appointments.component';

const routes: Routes = [

  {
    path:'',
    component:AppointmentListComponent
  },

  {
    path:'add',
    component:AppointmentAddComponent
  },

  {
    path:'edit/:id',
    component:AppointmentEditComponent
  },

  {
    path:'view/:id',
    component:AppointmentViewComponent
  },
  {
    path: 'my',
    component: MyAppointmentsComponent
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AppointmentRoutingModule { }