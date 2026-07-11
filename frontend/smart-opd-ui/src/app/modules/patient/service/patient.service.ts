import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

import { ApiConstants } from 'src/app/core/constants/api.constants';

import { Patient } from '../model/patient';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  constructor(
    private http: HttpClient
  ) { }

  getPatients(): Observable<Patient[]> {

    return this.http.get<Patient[]>(
      ApiConstants.BASE_URL_GATEWAY + '/patients'
    );

  }

  getPatient(id: number): Observable<Patient> {

    return this.http.get<Patient>(
      ApiConstants.BASE_URL_GATEWAY + '/patients/' + id
    );

  }

  createPatient(patient: Patient) {

    return this.http.post(
      ApiConstants.BASE_URL_GATEWAY + '/patients',
      patient
    );

  }

  updatePatient(id: number, patient: Patient) {

    return this.http.put(
      ApiConstants.BASE_URL_GATEWAY + '/patients/' + id,
      patient
    );

  }

  deletePatient(id: number) {

    return this.http.delete(
      ApiConstants.BASE_URL_GATEWAY + '/api/v1/patients/' + id
    );

  }

}