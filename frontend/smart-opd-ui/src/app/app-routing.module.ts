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

  // {
  //   path: 'dashboard',
  //   component: DashboardComponent
  // },
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
      path: 'profile',
      component: ProfileComponent
  },
  {
      path:'change-password',
      component:ChangePasswordComponent
  },
  {
    path: '',
    component: MainLayoutComponent,
    children: [

      {
        path: 'dashboard',
        component: DashboardComponent
      }

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