import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { DoctorService } from '../../services/doctor.service';

@Component({
  selector: 'app-doctor-view',
  templateUrl: './doctor-view.component.html'
})
export class DoctorViewComponent implements OnInit {

  doctor: any;

  constructor(
    private route: ActivatedRoute,
    private doctorService: DoctorService
  ) { }

  ngOnInit(): void {

    const id = Number(
      this.route.snapshot.paramMap.get('id')
    );

    this.doctorService.getDoctor(id)
      .subscribe({

        next: (response) => {

          this.doctor = response;

        }

      });

  }

}