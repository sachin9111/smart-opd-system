import { Component, OnInit } from '@angular/core';
import { AppointmentService } from '../../services/appointment.service';
import { Appointment } from '../../models/appointment';

@Component({
  selector: 'app-appointment-list',
  templateUrl: './appointment-list.component.html',
  styleUrls: ['./appointment-list.component.scss']
})
export class AppointmentListComponent implements OnInit {

  appointments: Appointment[] = [];
  filteredAppointments: Appointment[] = [];

  searchText = '';

  loading = false;

  constructor(private appointmentService: AppointmentService) {}

  ngOnInit(): void {
    this.loadAppointments();
  }

  loadAppointments(): void {

    this.loading = true;

    this.appointmentService.getAll().subscribe({

      next: (response) => {
        this.appointments = response;
        this.filteredAppointments = response;
        this.loading = false;
      },

      error: () => {
        this.loading = false;
      }

    });

  }

  search() {

  const text = this.searchText.toLowerCase();

  this.filteredAppointments = this.appointments.filter(a =>
      a.doctorId?.toString().includes(text) ||
      a.tokenNumber?.toString().includes(text) ||
      a.status?.toLowerCase().includes(text)
  );

}

  cancelAppointment(id: number) {

    if (!confirm('Cancel this appointment?')) {
      return;
    }

    this.appointmentService.cancel(id).subscribe(() => {
      this.loadAppointments();
    });

  }

}