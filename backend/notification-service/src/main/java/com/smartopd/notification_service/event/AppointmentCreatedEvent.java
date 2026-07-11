package com.smartopd.notification_service.event;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AppointmentCreatedEvent {

    private Long appointmentId;

    private Long patientUserId;

    private Long doctorId;

    private LocalDate appointmentDate;

    private LocalTime appointmentTime;

    private Integer tokenNumber;

    private String reason;

}