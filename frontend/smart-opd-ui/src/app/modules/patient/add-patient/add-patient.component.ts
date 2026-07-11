import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PatientService } from '../service/patient.service';
import { UserService } from 'src/app/core/services/user.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { User } from '../model/user';

@Component({
  selector: 'app-add-patient',
  templateUrl: './add-patient.component.html',
  styleUrls: ['./add-patient.component.scss']
})
export class AddPatientComponent {

  users: User[] = [];
  patientForm!: FormGroup;

  constructor( private fb: FormBuilder,
    private patientService: PatientService,private userService: UserService,
    private toastr: ToastrService, private router: Router

) { }

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

    this.loadUsers();

}

loadUsers() {

    this.userService.getUsers()

        .subscribe({

            next: (response) => {

                this.users = response;

            }

        });

}

save() {

    if (this.patientForm.invalid) {

        return;

    }

    this.patientService.createPatient(

        this.patientForm.value

    ).subscribe({

        next: () => {

            this.toastr.success("Patient Created");

            this.router.navigate(['/patients']);

        },

        error: () => {

            this.toastr.error("Unable to create patient");

        }

    });

}

}
