package com.example.kafka.Consumers;

import com.example.kafka.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class UserConsumer {
    private final Logger logger = LoggerFactory.getLogger(UserConsumer.class);

    @KafkaListener(id = "user", topics = "user", groupId = "user-0")
    public void consume(User user) {
        logger.info("Received user: " + user.toString());
    }
}
