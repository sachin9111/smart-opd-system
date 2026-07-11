import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './modules/auth/login/login.component';
import { DashboardComponent } from './modules/dashboard/dashboard.component';
import { RegisterComponent } from './modules/auth/register/register.component';
import { ForgotPasswordComponent } from './modules/auth/forgot-password/forgot-password.component';
import { VerifyOtpComponent } from './modules/auth/verify-otp/verify-otp.component';
import { ResetPasswordComponent } from './modules/auth/reset-password/reset-password.component';
import { MainLayoutComponent } from './layout/main-layout/main-layout.component';
import { ProfileComponent } from './modules/profile/profile.component';
import { ChangePasswordComponent } from './modules/change-password/change-password.component';
import { AuthGuard } from './core/guards/auth.guard';
import { DoctorListComponent } from './modules/doctor/components/doctor-list/doctor-list.component';
import { DoctorAddComponent } from './modules/doctor/components/doctor-add/doctor-add.component';
import { DoctorEditComponent } from './modules/doctor/components/doctor-edit/doctor-edit.component';
import { DoctorViewComponent } from './modules/doctor/components/doctor-view/doctor-view.component';

const routes: Routes = [

  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },

  {
    path: 'login',
    component: LoginComponent
  },

  {
    path:'register',
    component:RegisterComponent
  },
  {
    path: 'forgot-password',
    component: ForgotPasswordComponent
  },

  {
    path: 'verify-otp',
    component: VerifyOtpComponent
  },

  {
    path: 'reset-password',
    component: ResetPasswordComponent
  },

  {
    path: 'doctors',
    component: DoctorListComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'doctors/add',
    component: DoctorAddComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'doctors/edit/:id',
    component: DoctorEditComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'doctors/view/:id',
    component: DoctorViewComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'patients',
    loadChildren: () =>
        import('./modules/patient/patient.module')
        .then(m => m.PatientModule)
  },
  
  {
    path: '',
    component: MainLayoutComponent,
    canActivate: [AuthGuard],
    children: [

      {
        path: 'dashboard',
        component: DashboardComponent
      },
      {
        path: 'profile',
        component: ProfileComponent
      },
      {
          path:'change-password',
          component:ChangePasswordComponent
      },

    ]
  },

  {
    path: '**',
    redirectTo: 'login'
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }