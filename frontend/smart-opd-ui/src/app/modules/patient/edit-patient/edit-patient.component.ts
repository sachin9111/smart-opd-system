import { Component } from '@angular/core';
import { PatientService } from '../service/patient.service';
import { UserService } from 'src/app/core/services/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from '../model/user';

@Component({
  selector: 'app-edit-patient',
  templateUrl: './edit-patient.component.html',
  styleUrls: ['./edit-patient.component.scss']
})
export class EditPatientComponent {

  patientForm!: FormGroup;

  users: User[] = [];

  patientId!: number;

  constructor(
  private fb: FormBuilder,
  private patientService: PatientService,
  private userService: UserService,
  private route: ActivatedRoute,
  private router: Router,
  private toastr: ToastrService
) {}

ngOnInit(): void {

  this.patientForm = this.fb.group({

    userId: ['', Validators.required],

    bloodGroup: ['', Validators.required],

    height: ['', Validators.required],

    weight: ['', Validators.required],

    allergies: [''],

    medicalHistory: [''],

    emergencyContactName: ['', Validators.required],

    emergencyContactNumber: ['', Validators.required],

    insuranceNumber: [''],

    active: [true]

  });

  this.patientId = Number(this.route.snapshot.paramMap.get('id'));

  this.loadUsers();

  this.loadPatient();

}

loadUsers() {

  this.userService.getUsers().subscribe({

    next: response => {

      this.users = response;

    }

  });

}

loadPatient() {

  this.patientService.getPatient(this.patientId)

    .subscribe({

      next: response => {

        this.patientForm.patchValue(response);

      }

    });

}

update() {

  if (this.patientForm.invalid) {

    return;

  }

  this.patientService.updatePatient(

    this.patientId,

    this.patientForm.value

  ).subscribe({

    next: () => {

      this.toastr.success("Patient Updated Successfully");

      this.router.navigate(['/patients']);

    },

    error: () => {

      this.toastr.error("Unable to update patient");

    }

  });

}

}
