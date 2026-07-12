import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Appointment } from '../models/appointment';
import { CreateAppointment } from '../models/create-appointment';
import { DoctorService } from '../../doctor/services/doctor.service';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  private apiUrl = 'http://localhost:8081/api/v1/appointments';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Appointment[]> {
    return this.http.get<Appointment[]>(this.apiUrl);
  }

  getMyAppointments(): Observable<Appointment[]> {
    return this.http.get<Appointment[]>(`${this.apiUrl}/my`);
  }

  getById(id: number): Observable<Appointment> {
    return this.http.get<Appointment>(`${this.apiUrl}/${id}`);
  }

  create(request: CreateAppointment) {
    return this.http.post(this.apiUrl, request);
  }

  cancel(id: number) {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}