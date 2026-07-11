import { Component, OnInit } from '@angular/core';

import { DoctorService } from '../../services/doctor.service';

import { Doctor } from '../../models/doctor';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-doctor-list',
  templateUrl: './doctor-list.component.html'
})
export class DoctorListComponent implements OnInit {

  doctors: Doctor[] = [];

  constructor(
    private doctorService: DoctorService,
    private toastr: ToastrService
  ) { }

  ngOnInit(): void {

    this.loadDoctors();

  }

  loadDoctors(): void {

    this.doctorService.getAllDoctors()
      .subscribe({

        next: (response) => {

          this.doctors = response;

          console.log(response);

        },

        error: (err) => {

          console.log(err);

        }

      });

  }

  deleteDoctor(id: number): void {

  if (!confirm('Are you sure you want to delete this doctor?')) {
    return;
  }

  this.doctorService.deleteDoctor(id)
    .subscribe({

      next: () => {

        this.toastr.success('Doctor deleted successfully');

        this.loadDoctors();

      },

      error: () => {

        this.toastr.error('Unable to delete doctor');

      }

    });

}

}