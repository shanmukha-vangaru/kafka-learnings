package com.example.kafka.Consumers;

import com.example.kafka.mappers.UserClicksMapper;
import com.example.kafka.models.UserClicks;
import com.example.kafka.respository.UserClicksRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserClicksConsumer {
    private final Logger logger = LoggerFactory.getLogger(UserClicksConsumer.class);

    @Autowired
    private UserClicksRepository userClicksRepository;

    @Autowired
    private ModelMapper modelMapper;

    @KafkaListener(id = "user-clicks", topics = "user-clicks", groupId = "user-clicks-0")
    public void consume(UserClicks userClicks) {
        logger.info("Received User Clicks: " + userClicks.toString());
        com.example.kafka.entities.UserClicks entity = UserClicksMapper.getEntity(userClicks);
        userClicksRepository.save(entity);
    }
}
