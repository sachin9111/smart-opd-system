package com.smartopd.notification_service.config;

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

import com.smartopd.notification_service.event.AppointmentCreatedEvent;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

	@Bean
	ConsumerFactory<String, AppointmentCreatedEvent> consumerFactory(KafkaProperties properties) {

		Map<String, Object> config = new HashMap<>(properties.buildConsumerProperties());

		config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

		JsonDeserializer<AppointmentCreatedEvent> deserializer = new JsonDeserializer<>(AppointmentCreatedEvent.class);

		deserializer.addTrustedPackages("*");
		deserializer.setUseTypeHeaders(false);

		return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), deserializer);
	}

	@Bean
	ConcurrentKafkaListenerContainerFactory<String, AppointmentCreatedEvent> kafkaListenerContainerFactory(
			ConsumerFactory<String, AppointmentCreatedEvent> consumerFactory) {

		ConcurrentKafkaListenerContainerFactory<String, AppointmentCreatedEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();

		factory.setConsumerFactory(consumerFactory);

		return factory;
	}

}