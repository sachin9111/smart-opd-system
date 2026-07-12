import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { ToastrModule } from 'ngx-toastr';
import { LoginModule } from './modules/auth/login/login.module';
import { RegisterModule } from './modules/auth/register/register.module';
import { ForgotPasswordModule } from './modules/auth/forgot-password/forgot-password.module';
import { VerifyOtpModule } from './modules/auth/verify-otp/verify-otp.module';
import { ResetPasswordModule } from './modules/auth/reset-password/reset-password.module';

import { MainLayoutModule } from './layout/main-layout/main-layout.module';
import { DashboardModule } from './modules/dashboard/dashboard.module';
import { ProfileModule } from './modules/profile/profile.module';
import { ChangePasswordModule } from './modules/change-password/change-password.module';
import { AuthInterceptor } from './core/interceptors/auth.interceptor';
import { DoctorModule } from './modules/doctor/doctor.module';
import { PatientModule } from './modules/patient/patient.module';
import { DoctorQueueComponent } from './modules/queue/components/doctor-queue/doctor-queue.component';


@NgModule({
  declarations: [
    AppComponent,
    DoctorQueueComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    LoginModule,
    RegisterModule,
    ForgotPasswordModule,
    VerifyOtpModule,
    ResetPasswordModule,
    MainLayoutModule,
    DashboardModule,
    DoctorModule,
    PatientModule,
    ProfileModule,
    ChangePasswordModule,
    ToastrModule.forRoot({
      positionClass: 'toast-top-right',
      preventDuplicates: true,
      closeButton: true,
      progressBar: true

    })
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
