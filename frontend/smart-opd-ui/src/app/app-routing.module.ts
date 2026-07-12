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
    path: 'patients',
    component:MainLayoutComponent,
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
      {
          path: 'doctors',
          loadChildren: () =>
              import('./modules/doctor/doctor.module')
              .then(m => m.DoctorModule)
      },
      {
          path:'appointments',
          loadChildren:()=>
            import('./modules/appointment/appointment.module')
            .then(m=>m.AppointmentModule)
      },
      {
          path:'queue',
          loadChildren:()=> 
          import('./modules/queue/queue.module')
          .then(m=>m.QueueModule)
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