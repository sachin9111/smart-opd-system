import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { ToastrModule } from 'ngx-toastr';
import { DashboardComponent } from './modules/dashboard/dashboard.component';
import { LoginModule } from './modules/auth/login/login.module';
import { RegisterComponent } from './modules/auth/register/register.component';
import { RegisterModule } from './modules/auth/register/register.module';
import { ForgotPasswordComponent } from './modules/auth/forgot-password/forgot-password.component';
import { VerifyOtpComponent } from './modules/auth/verify-otp/verify-otp.component';
import { ForgotPasswordModule } from './modules/auth/forgot-password/forgot-password.module';
import { VerifyOtpModule } from './modules/auth/verify-otp/verify-otp.module';
import { ResetPasswordComponent } from './modules/auth/reset-password/reset-password.component';
import { ResetPasswordModule } from './modules/auth/reset-password/reset-password.module';
import { MainLayoutComponent } from './layout/main-layout/main-layout.component';
import { HeaderComponent } from './layout/header/header.component';
import { SidebarComponent } from './layout/sidebar/sidebar.component';
import { FooterComponent } from './layout/footer/footer.component';
import { MainLayoutModule } from './layout/main-layout/main-layout.module';
import { DashboardModule } from './modules/dashboard/dashboard.module';
import { ProfileComponent } from './modules/profile/profile.component';
import { ProfileModule } from './modules/profile/profile.module';
import { ChangePasswordComponent } from './modules/change-password/change-password.component';
import { ChangePasswordModule } from './modules/change-password/change-password.module';

@NgModule({
  declarations: [
    AppComponent,
    
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
    ProfileModule,
    ChangePasswordModule,
    ToastrModule.forRoot({
      positionClass: 'toast-top-right',
      preventDuplicates: true,
      closeButton: true,
      progressBar: true

    })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
