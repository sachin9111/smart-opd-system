import { Component } from '@angular/core';
import { PatientService } from '../service/patient.service';
import { ActivatedRoute } from '@angular/router';
import { Patient } from '../model/patient';

@Component({
  selector: 'app-view-patient',
  templateUrl: './view-patient.component.html',
  styleUrls: ['./view-patient.component.scss']
})
export class ViewPatientComponent {

  patient!: Patient;
  constructor(

  private patientService: PatientService,

  private route: ActivatedRoute

) { }

ngOnInit(): void {

  const id = Number(

      this.route.snapshot.paramMap.get('id')

  );

  this.patientService.getPatient(id)

      .subscribe({

          next: response => {

              this.patient = response;

          }

      });

}

}
