import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

import { ApiConstants } from '../constants/api.constants';

import { User } from 'src/app/modules/patient/model/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private http: HttpClient
  ) { }

  getUsers(): Observable<User[]> {

    return this.http.get<User[]>(
      ApiConstants.BASE_URL_GATEWAY + ApiConstants.PATIENT.LIST
    );

  }

}