package com.smartopd.user_service.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.smartopd.user_service.event.UserCreatedEvent;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Bean
    ConsumerFactory<String, UserCreatedEvent> consumerFactory(KafkaProperties properties) {

        Map<String, Object> config = new HashMap<>(properties.buildConsumerProperties());

        JsonDeserializer<UserCreatedEvent> deserializer =
                new JsonDeserializer<>(UserCreatedEvent.class);

        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeHeaders(false);

        return new DefaultKafkaConsumerFactory<>(
                config,
                new StringDeserializer(),
                deserializer
        );
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, UserCreatedEvent> kafkaListenerContainerFactory(
            ConsumerFactory<String, UserCreatedEvent> consumerFactory) {

        ConcurrentKafkaListenerContainerFactory<String, UserCreatedEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory);

        return factory;
    }
}