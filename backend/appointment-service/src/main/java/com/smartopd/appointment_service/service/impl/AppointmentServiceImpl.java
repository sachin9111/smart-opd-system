package com.smartopd.appointment_service.service.impl;

import com.smartopd.appointment_service.dto.AppointmentResponse;
import com.smartopd.appointment_service.dto.CreateAppointmentRequest;
import com.smartopd.appointment_service.entity.Appointment;
import com.smartopd.appointment_service.entity.AppointmentStatus;
import com.smartopd.appointment_service.event.AppointmentCreatedEvent;
import com.smartopd.appointment_service.producer.AppointmentProducer;
import com.smartopd.appointment_service.repository.AppointmentRepository;
import com.smartopd.appointment_service.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository repository;
    
    private final AppointmentProducer appointmentProducer;

    @Override
    public AppointmentResponse createAppointment(
            Long patientUserId,
            CreateAppointmentRequest request) {
    	
    	

    	
        
        boolean alreadyBooked =
                repository.existsByDoctorIdAndAppointmentDateAndAppointmentTime(
                        request.getDoctorId(),
                        request.getAppointmentDate(),
                        request.getAppointmentTime()
                );

        if (alreadyBooked) {
            throw new RuntimeException(
                    "Doctor already has an appointment at this time."
            );
        }
        
        int tokenNumber =
                repository.countByDoctorIdAndAppointmentDate(
                        request.getDoctorId(),
                        request.getAppointmentDate()
                ) + 1;
        
        Appointment appointment = Appointment.builder()
    	        .patientUserId(patientUserId)
    	        .doctorId(request.getDoctorId())
    	        .appointmentDate(request.getAppointmentDate())
    	        .appointmentTime(request.getAppointmentTime())
    	        .reason(request.getReason())
    	        .status(AppointmentStatus.BOOKED)
    	        .tokenNumber(tokenNumber)
    	        .build();

        appointment = repository.save(appointment);
        
        AppointmentCreatedEvent event = AppointmentCreatedEvent.builder()
                        .appointmentId(appointment.getId())
                        .patientUserId(appointment.getPatientUserId())
                        .doctorId(appointment.getDoctorId())
                        .appointmentDate(appointment.getAppointmentDate())
                        .appointmentTime(appointment.getAppointmentTime())
                        .tokenNumber(appointment.getTokenNumber())
                        .reason(appointment.getReason())
                        .build();

        appointmentProducer.publish(event);

        return mapToResponse(appointment);
    }

    @Override
    public AppointmentResponse getAppointment(Long id) {

        Appointment appointment = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Appointment not found"));

        return mapToResponse(appointment);
    }

    @Override
    public List<AppointmentResponse> getAllAppointments() {

        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<AppointmentResponse> getPatientAppointments(Long patientUserId) {

        return repository.findByPatientUserId(patientUserId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<AppointmentResponse> getDoctorAppointments(Long doctorId) {

        return repository.findByDoctorId(doctorId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public void cancelAppointment(Long id) {

        Appointment appointment = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Appointment not found"));

        appointment.setStatus(AppointmentStatus.CANCELLED);

        repository.save(appointment);
    }

    private AppointmentResponse mapToResponse(Appointment appointment) {

        return AppointmentResponse.builder()
                .id(appointment.getId())
                .patientUserId(appointment.getPatientUserId())
                .doctorId(appointment.getDoctorId())
                .appointmentDate(appointment.getAppointmentDate())
                .appointmentTime(appointment.getAppointmentTime())
                .reason(appointment.getReason())
                .status(appointment.getStatus())
                .tokenNumber(appointment.getTokenNumber())
                .build();
    }
}