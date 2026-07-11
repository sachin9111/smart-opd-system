package com.smartopd.notification_service.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.smartopd.notification_service.event.AppointmentCreatedEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AppointmentConsumer {

    @KafkaListener(
            topics = "appointment-created",
            groupId = "notification-service"
    )
    public void consume(AppointmentCreatedEvent event) {

        log.info("======================================");
        log.info("Appointment Received");
        log.info("{}", event);
        log.info("======================================");

    }

}