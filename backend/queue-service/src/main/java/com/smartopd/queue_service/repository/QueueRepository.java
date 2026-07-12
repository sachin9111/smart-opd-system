package com.smartopd.queue_service.repository;

import com.smartopd.queue_service.entity.QueueEntry;
import com.smartopd.queue_service.enums.QueueStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface QueueRepository extends JpaRepository<QueueEntry, Long> {

    // Complete queue of a doctor for a day
    List<QueueEntry> findByDoctorIdAndAppointmentDateOrderByTokenNumberAsc(
            Long doctorId,
            LocalDate appointmentDate
    );

    // Next waiting patient
    Optional<QueueEntry> findFirstByDoctorIdAndAppointmentDateAndStatusOrderByTokenNumberAsc(
            Long doctorId,
            LocalDate appointmentDate,
            QueueStatus status
    );

    // Current patient
    Optional<QueueEntry> findByDoctorIdAndAppointmentDateAndStatus(
            Long doctorId,
            LocalDate appointmentDate,
            QueueStatus status
    );

    // Dashboard counts
    long countByDoctorIdAndAppointmentDateAndStatus(
            Long doctorId,
            LocalDate appointmentDate,
            QueueStatus status
    );

    // Highest token issued today
    Optional<QueueEntry> findTopByDoctorIdAndAppointmentDateOrderByTokenNumberDesc(
            Long doctorId,
            LocalDate appointmentDate
    );
    
    List<QueueEntry> findByPatientUserId(Long patientUserId);

}