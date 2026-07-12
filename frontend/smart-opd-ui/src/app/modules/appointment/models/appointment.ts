export interface Appointment {

  id?: number;

  patientUserId?: number;

  doctorId: number;

  appointmentDate: string;

  appointmentTime: string;

  reason?: string;

  status?: string;

  tokenNumber?: number;

}