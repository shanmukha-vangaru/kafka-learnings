package com.example.kafka.Consumers;

import com.example.kafka.mappers.UserClicksMapper;
import com.example.kafka.models.Click;
import com.example.kafka.models.UserClicks;
import com.example.kafka.respository.UserClicksRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class DLDConsumer {

    private final Logger logger = LoggerFactory.getLogger(DLDConsumer.class);

    @Autowired
    private UserClicksRepository userClicksRepository;

    @KafkaListener(groupId = "user-clicks-0", topics = "user-clicks-dlt")
    public void consume(UserClicks userClicks) {
        logger.info("Received User Clicks in DLD Consumer: " + userClicks.toString());
        userClicks.setClickType(Click.CATEGORY_CLICK);
        com.example.kafka.entities.UserClicks entity = UserClicksMapper.getEntity(userClicks);
        userClicksRepository.save(entity);
        logger.info("DLD record saved to db");
    }

}
