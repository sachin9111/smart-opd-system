package com.smartopd.appointment_service.producer;

import com.smartopd.appointment_service.event.AppointmentCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppointmentProducer {

    private final KafkaTemplate<String, AppointmentCreatedEvent> kafkaTemplate;

    private static final String TOPIC = "appointment-created";

    public void publish(AppointmentCreatedEvent event) {

        log.info("Publishing Appointment Event : {}", event);

        kafkaTemplate.send(TOPIC, event);

    }

}