import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

import { ApiConstants } from 'src/app/core/constants/api.constants';

import { Doctor } from '../models/doctor';

import { DoctorRequest } from '../models/doctor-request';

@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  constructor(
    private http: HttpClient
  ) { }

  getAllDoctors(): Observable<Doctor[]> {

    return this.http.get<Doctor[]>(
      ApiConstants.BASE_URL_GATEWAY +
      ApiConstants.DOCTOR.GET_ALL
    );

  }

  getDoctor(id: number): Observable<Doctor> {

    return this.http.get<Doctor>(
      ApiConstants.BASE_URL_GATEWAY +
      ApiConstants.DOCTOR.GET_BY_ID +
      '/' +
      id
    );

  }

  createDoctor(request: DoctorRequest): Observable<Doctor> {

    return this.http.post<Doctor>(
      ApiConstants.BASE_URL_GATEWAY +
      ApiConstants.DOCTOR.CREATE,
      request
    );

  }

  updateDoctor(
    id: number,
    request: DoctorRequest
  ): Observable<Doctor> {

    return this.http.put<Doctor>(
      ApiConstants.BASE_URL_GATEWAY +
      ApiConstants.DOCTOR.UPDATE +
      '/' +
      id,
      request
    );

  }

  deleteDoctor(id: number): Observable<any> {

    return this.http.delete(
      ApiConstants.BASE_URL_GATEWAY +
      ApiConstants.DOCTOR.DELETE +
      '/' +
      id
    );

  }

}