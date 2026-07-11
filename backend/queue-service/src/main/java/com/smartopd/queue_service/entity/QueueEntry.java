package com.smartopd.queue_service.entity;

import java.time.LocalDate;

import com.smartopd.queue_service.enums.QueueStatus;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "queue_entries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QueueEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long appointmentId;

    private Long patientUserId;

    private Long doctorId;

    private LocalDate appointmentDate;

    private Integer tokenNumber;

    @Enumerated(EnumType.STRING)
    private QueueStatus status;

}