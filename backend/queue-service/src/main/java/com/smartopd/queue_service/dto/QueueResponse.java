package com.smartopd.queue_service.dto;

import com.smartopd.queue_service.enums.QueueStatus;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueueResponse {

    private Long id;

    private Long appointmentId;

    private Long patientUserId;

    private Long doctorId;

    private LocalDate appointmentDate;

    private Integer tokenNumber;

    private QueueStatus status;

    // From User Service
    private String firstName;

    private String lastName;

    private String mobile;

    private String profilePhoto;

}