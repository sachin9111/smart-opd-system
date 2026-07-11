import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators
} from '@angular/forms';

import { ActivatedRoute, Router } from '@angular/router';

import { ToastrService } from 'ngx-toastr';

import { DoctorService } from '../../services/doctor.service';

@Component({
  selector: 'app-doctor-edit',
  templateUrl: './doctor-edit.component.html'
})
export class DoctorEditComponent implements OnInit {

  doctorId!: number;

  doctorForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private doctorService: DoctorService,
    private route: ActivatedRoute,
    private router: Router,
    private toastr: ToastrService
  ) { }

  ngOnInit(): void {

    this.createForm();

    this.doctorId = Number(
      this.route.snapshot.paramMap.get('id')
    );

    this.loadDoctor();

  }

  createForm(): void {

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

  loadDoctor(): void {

    this.doctorService.getDoctor(this.doctorId)
      .subscribe({

        next: (response) => {

          this.doctorForm.patchValue(response);

        },

        error: () => {

          this.toastr.error('Unable to load doctor');

        }

      });

  }

  updateDoctor(): void {

    if (this.doctorForm.invalid) {

      this.doctorForm.markAllAsTouched();

      return;

    }

    this.doctorService.updateDoctor(
      this.doctorId,
      this.doctorForm.value
    ).subscribe({

      next: () => {

        this.toastr.success(
          'Doctor Updated Successfully'
        );

        this.router.navigate(['/doctors']);

      },

      error: () => {

        this.toastr.error(
          'Unable to update doctor'
        );

      }

    });

  }

}