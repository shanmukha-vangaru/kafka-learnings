package com.example.kafka.Config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic calculation() {
        return TopicBuilder.name("calculation").partitions(5).replicas(3).build();
    }

    @Bean
    public NewTopic user() {
        return TopicBuilder.name("user").partitions(5).replicas(3).build();
    }
}
