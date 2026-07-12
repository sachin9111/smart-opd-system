import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AppointmentService } from '../../services/appointment.service';
import { Appointment } from '../../models/appointment';

@Component({
  selector: 'app-appointment-view',
  templateUrl: './appointment-view.component.html',
  styleUrls: ['./appointment-view.component.scss']
})
export class AppointmentViewComponent implements OnInit {

  appointment?: Appointment;

  constructor(
    private route: ActivatedRoute,
    private appointmentService: AppointmentService
  ) { }

  ngOnInit(): void {

    const id = Number(this.route.snapshot.paramMap.get('id'));

    this.loadAppointment(id);

  }

  loadAppointment(id: number): void {

    this.appointmentService.getById(id).subscribe({

      next: (response) => {

        this.appointment = response;

      },

      error: (err) => {

        console.error(err);

      }

    });

  }

}