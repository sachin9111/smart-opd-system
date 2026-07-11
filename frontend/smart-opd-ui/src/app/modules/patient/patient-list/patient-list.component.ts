import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';

import { ToastrService } from 'ngx-toastr';

import { PatientService } from '../service/patient.service';

import { Patient } from '../model/patient';

@Component({

  selector: 'app-patient-list',

  templateUrl: './patient-list.component.html',

  styleUrls: ['./patient-list.component.scss']

})

export class PatientListComponent implements OnInit {

  patients: Patient[] = [];

  loading = false;

  constructor(

    private patientService: PatientService,

    private toastr: ToastrService,

    private router: Router

  ) { }

  ngOnInit(): void {

    this.loadPatients();

  }

  loadPatients() {

    this.loading = true;

    this.patientService.getPatients().subscribe({

      next: (response) => {

        this.patients = response;

        this.loading = false;

      },

      error: () => {

        this.loading = false;

        this.toastr.error("Unable to load patients");

      }

    });

  }

  addPatient() {

    this.router.navigate(['/patients/add']);

  }

  editPatient(id: number) {

    this.router.navigate(['/patients/edit', id]);

  }

  viewPatient(id: number) {

    this.router.navigate(['/patients/view', id]);

  }

  deletePatient(id: number) {

    if (!confirm("Delete Patient?"))

      return;

    this.patientService.deletePatient(id)

      .subscribe({

        next: () => {

          this.toastr.success("Patient Deleted");

          this.loadPatients();

        },

        error: () => {

          this.toastr.error("Delete Failed");

        }

      });

  }

}