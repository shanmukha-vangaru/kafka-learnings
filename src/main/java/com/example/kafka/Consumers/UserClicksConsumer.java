package com.example.kafka.Consumers;

import com.example.kafka.mappers.UserClicksMapper;
import com.example.kafka.models.Click;
import com.example.kafka.models.UserClicks;
import com.example.kafka.respository.UserClicksRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

@Service
public class UserClicksConsumer {
    private final Logger logger = LoggerFactory.getLogger(UserClicksConsumer.class);

    @Autowired
    private UserClicksRepository userClicksRepository;

    @Autowired
    private ModelMapper modelMapper;

    @KafkaListener(id = "user-clicks", topics = "user-clicks", groupId = "user-clicks-0")
    @RetryableTopic(
            backoff = @Backoff(value = 1000L),
            attempts = "2",
            autoCreateTopics = "true",
            include = RuntimeException.class
    )
    public void consume(UserClicks userClicks) {
        com.example.kafka.entities.UserClicks entity = UserClicksMapper.getEntity(userClicks);
        logger.info("Received User Clicks: " + userClicks.toString());
        if(entity.getClickType().toString().equals(Click.PRODUCT_CLICK.toString())) {
            throw new RuntimeException("Invalid click type");
        }
        userClicksRepository.save(entity);
    }
}
