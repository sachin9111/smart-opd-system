import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

import { AuthService } from '../../core/services/auth.service';
import { ChangePasswordRequest } from '../../core/models/change-password-request';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss']
})
export class ChangePasswordComponent {

  loading=false;

  hideCurrent=true;

  hideNew=true;

  hideConfirm=true;

  form=this.fb.group({

      currentPassword:['',[Validators.required]],

      newPassword:['',[Validators.required,Validators.minLength(8)]],

      confirmPassword:['',[Validators.required]]

  });

  constructor(
      private fb:FormBuilder,
      private authService:AuthService,
      private toastr:ToastrService
  ){ }

  changePassword(){

      if(this.form.invalid){

          this.form.markAllAsTouched();

          return;

      }

      if(this.f.newPassword.value!==this.f.confirmPassword.value){

          this.toastr.error('Passwords do not match.');

          return;

      }

      this.loading=true;

      const request:ChangePasswordRequest={

          currentPassword:this.f.currentPassword.value!,

          newPassword:this.f.newPassword.value!

      };

      this.authService.changePassword(request)
      .subscribe({

          next:()=>{

              this.loading=false;

              this.toastr.success('Password changed successfully.');

              this.form.reset();

          },

          error:()=>{

              this.loading=false;

              this.toastr.error('Unable to change password.');

          }

      });

  }

  get f(){

      return this.form.controls;

  }

}