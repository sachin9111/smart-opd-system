package com.smartopd.auth_service.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.smartopd.auth_service.event.UserCreatedEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publish(UserCreatedEvent event) {

        kafkaTemplate.send("user-created", event);

        log.info("Published UserCreatedEvent : {}", event);
    }
}