import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

import { Queue } from '../models/queue';
import { QueueDashboard } from '../models/queue-dashboard';


@Injectable({
  providedIn: 'root'
})
export class QueueService {


  private apiUrl = "http://localhost:8081/api/v1/queue";


  constructor(
    private http: HttpClient
  ) { }


  getMyQueue(){

    return this.http.get<Queue[]>(
    `${this.apiUrl}/my`
    );

  }



  getDoctorQueue(doctorId: number): Observable<Queue[]> {

    return this.http.get<Queue[]>(
      `${this.apiUrl}/doctor/${doctorId}`
    );

  }



  getDashboard(doctorId: number): Observable<QueueDashboard> {

    return this.http.get<QueueDashboard>(
      `${this.apiUrl}/doctor/${doctorId}/dashboard`
    );

  }



  nextPatient(doctorId: number) {

    return this.http.put<Queue>(
      `${this.apiUrl}/doctor/${doctorId}/next`,
      {}
    );

  }



  complete(id: number) {

    return this.http.put<Queue>(
      `${this.apiUrl}/${id}/complete`,
      {}
    );

  }



  skip(id: number) {

    return this.http.put<Queue>(
      `${this.apiUrl}/${id}/skip`,
      {}
    );

  }



  recall(id: number) {

    return this.http.put<Queue>(
      `${this.apiUrl}/${id}/recall`,
      {}
    );

  }


}