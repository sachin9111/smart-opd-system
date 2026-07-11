package com.smartopd.queue_service.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.smartopd.queue_service.entity.QueueEntry;
import com.smartopd.queue_service.enums.QueueStatus;
import com.smartopd.queue_service.event.AppointmentCreatedEvent;
import com.smartopd.queue_service.repository.QueueRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppointmentConsumer {

    private final QueueRepository queueRepository;

    @KafkaListener(
            topics = "appointment-created",
            groupId = "queue-service"
    )
    public void consume(AppointmentCreatedEvent event) {

        log.info("Creating Queue Entry...");

        QueueEntry queue = QueueEntry.builder()
                .appointmentId(event.getAppointmentId())
                .patientUserId(event.getPatientUserId())
                .doctorId(event.getDoctorId())
                .appointmentDate(event.getAppointmentDate())
                .tokenNumber(event.getTokenNumber())
                .status(QueueStatus.WAITING)
                .build();

        queueRepository.save(queue);

        log.info("Queue Entry Saved : {}", queue.getId());

    }

}