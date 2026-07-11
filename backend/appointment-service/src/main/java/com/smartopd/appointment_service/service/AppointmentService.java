package com.smartopd.appointment_service.service;

import com.smartopd.appointment_service.dto.AppointmentResponse;
import com.smartopd.appointment_service.dto.CreateAppointmentRequest;

import java.util.List;

public interface AppointmentService {

    AppointmentResponse createAppointment(
            Long patientUserId,
            CreateAppointmentRequest request);

    AppointmentResponse getAppointment(Long id);

    List<AppointmentResponse> getAllAppointments();

    List<AppointmentResponse> getPatientAppointments(Long patientUserId);

    List<AppointmentResponse> getDoctorAppointments(Long doctorId);

    void cancelAppointment(Long id);

}