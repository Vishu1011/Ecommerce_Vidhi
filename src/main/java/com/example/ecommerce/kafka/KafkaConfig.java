package com.example.ecommerce.kafka;

import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    public Map<String, Object> producerConfig() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put("bootstrap.servers", "localhost:9092"); // Kafka bootstrap server address
        configProps.put("key.serializer", StringSerializer.class); // Serializer for the key
        configProps.put("value.serializer", StringSerializer.class); // Serializer for the value
        return configProps;
    }
}

