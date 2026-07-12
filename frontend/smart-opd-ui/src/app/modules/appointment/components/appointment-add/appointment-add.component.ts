import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { AppointmentService } from '../../services/appointment.service';
import { Doctor } from 'src/app/modules/doctor/models/doctor';
import { DoctorService } from 'src/app/modules/doctor/services/doctor.service';

@Component({
  selector: 'app-appointment-add',
  templateUrl: './appointment-add.component.html',
  styleUrls: ['./appointment-add.component.scss']
})
export class AppointmentAddComponent implements OnInit {

  appointmentForm!: FormGroup;

  saving = false;

  doctors: Doctor[] = [];

  constructor(
    private fb: FormBuilder,
    private appointmentService: AppointmentService,
    private doctorService: DoctorService,
    private router: Router
  ) {}

  ngOnInit(): void {

    this.appointmentForm = this.fb.group({

      doctorId: ['', Validators.required],

      appointmentDate: ['', Validators.required],

      appointmentTime: ['', Validators.required],

      reason: ['']

    });

    // We'll load doctors in the next step
     this.loadDoctors();

  }

  loadDoctors(): void {

  this.doctorService.getAllDoctors().subscribe({

    next: (response) => {

      this.doctors = response;

    },

    error: (error) => {

      console.error(error);

      alert('Unable to load doctors.');

    }

  });

}

  save() {

    if(this.appointmentForm.invalid){

      this.appointmentForm.markAllAsTouched();

      return;

    }

    this.saving=true;

    this.appointmentService.create(this.appointmentForm.value)

      .subscribe({

        next:()=>{

          alert("Appointment booked successfully.");

          this.router.navigate(['/appointments']);

        },

        error:()=>{

          this.saving=false;

          alert("Something went wrong.");

        }

      });

  }

}