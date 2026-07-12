import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DoctorListComponent } from './components/doctor-list/doctor-list.component';
import { DoctorAddComponent } from './components/doctor-add/doctor-add.component';
import { DoctorEditComponent } from './components/doctor-edit/doctor-edit.component';
import { DoctorViewComponent } from './components/doctor-view/doctor-view.component';

const routes: Routes = [
  {
    path: '',
    component: DoctorListComponent
  },
  {
    path: 'add',
    component: DoctorAddComponent
  },
  {
    path: 'edit/:id',
    component: DoctorEditComponent
  },
  {
    path: 'view/:id',
    component: DoctorViewComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DoctorRoutingModule {}