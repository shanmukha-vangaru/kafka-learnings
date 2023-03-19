package com.example.kafka.Consumers;

import com.example.kafka.mappers.UserLoginsMapper;
import com.example.kafka.models.UserLogins;
import com.example.kafka.respository.UserLoginsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserLoginsConsumer {
    private final Logger logger = LoggerFactory.getLogger(UserLoginsConsumer.class);

    @Autowired
    private UserLoginsRepository userLoginsRepository;

    @KafkaListener(id = "user-logins", topics = "user-logins", groupId = "user-logins-0")
    public void consume(UserLogins userLogins) {
        logger.info("UserLogins: " + userLogins.toString());
        userLoginsRepository.save(UserLoginsMapper.getEntity(userLogins));
    }
}
