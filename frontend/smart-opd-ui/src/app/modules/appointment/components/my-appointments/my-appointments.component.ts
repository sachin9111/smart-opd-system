import { Component, OnInit } from '@angular/core';
import { Appointment } from '../../models/appointment';
import { AppointmentService } from '../../services/appointment.service';

@Component({
  selector: 'app-my-appointments',
  templateUrl: './my-appointments.component.html',
  styleUrls: ['./my-appointments.component.scss']
})
export class MyAppointmentsComponent implements OnInit {

  appointments: Appointment[] = [];

  loading = false;

  constructor(
    private appointmentService: AppointmentService
  ) {}

  ngOnInit(): void {

    this.loadAppointments();

  }

  loadAppointments() {

    this.loading = true;

    this.appointmentService.getMyAppointments().subscribe({

      next: (response) => {

        this.appointments = response;

        this.loading = false;

      },

      error: () => {

        this.loading = false;

      }

    });

  }

  cancel(id:number){

    if(!confirm('Cancel this appointment?')){

      return;

    }

    this.appointmentService.cancel(id)

    .subscribe(()=>{

      this.loadAppointments();

    });

  }

}