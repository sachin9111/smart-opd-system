package com.smartopd.user_service.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.smartopd.user_service.event.UserCreatedEvent;
import com.smartopd.user_service.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Component
public class UserCreatedConsumer {

    private final UserService userService;

    @KafkaListener(
            topics = "user-created",
            groupId = "user-service"
    )
    public void consume(UserCreatedEvent event) {

        log.info("======================================");
        log.info("Received User Created Event");
        log.info("{}", event);

        userService.createUser(event);

        log.info("======================================");
    }
}