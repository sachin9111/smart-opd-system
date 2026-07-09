import { Component, OnInit } from '@angular/core';

import { AuthService } from '../../core/services/auth.service';
import { UserInfo } from '../../core/models/user-info';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  user?: UserInfo;

  loading = false;

  constructor(
    private authService: AuthService
  ) {
  }

  ngOnInit(): void {

    this.loadProfile();

  }

  loadProfile(): void {

    this.loading = true;

    this.authService.getProfile().subscribe({

      next: (response) => {

        this.user = response;

        this.loading = false;

      },

      error: () => {

        this.loading = false;

      }

    });

  }

}