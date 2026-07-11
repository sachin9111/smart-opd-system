import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators
} from '@angular/forms';

import { Router } from '@angular/router';

import { ToastrService } from 'ngx-toastr';

import { DoctorService } from '../../services/doctor.service';

@Component({
  selector: 'app-doctor-add',
  templateUrl: './doctor-add.component.html'
})
export class DoctorAddComponent {

  doctorForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private doctorService: DoctorService,
    private toastr: ToastrService,
    private router: Router
  ) {

    this.doctorForm = this.fb.group({

      userId: ['', Validators.required],

      registrationNumber: ['', Validators.required],

      specialization: ['', Validators.required],

      qualification: [''],

      experience: ['', Validators.required],

      department: [''],

      consultationFee: ['', Validators.required],

      active: [true]

    });

  }

  saveDoctor(): void {

    if (this.doctorForm.invalid) {

      this.doctorForm.markAllAsTouched();

      return;

    }

    this.doctorService.createDoctor(
      this.doctorForm.value
    ).subscribe({

      next: () => {

        this.toastr.success(
          'Doctor Added Successfully'
        );

        this.router.navigate(['/doctors']);

      },

      error: () => {

        this.toastr.error(
          'Unable to save doctor'
        );

      }

    });

  }

}