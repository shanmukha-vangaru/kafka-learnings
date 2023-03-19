package com.example.kafka.Consumers;

import com.example.kafka.mappers.UserSearchesMapper;
import com.example.kafka.models.UserSearches;
import com.example.kafka.respository.UserSearchesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserSearchesConsumer {
    private final Logger logger = LoggerFactory.getLogger(UserSearchesConsumer.class);

    @Autowired
    private UserSearchesRepository userSearchesRepository;

    @KafkaListener(id = "user-searches", topics = "user-searches", groupId = "user-searches-0")
    public void consume(UserSearches userSearches) {
        logger.info("User Searches: " + userSearches.toString());
        userSearchesRepository.save(UserSearchesMapper.getEntity(userSearches));
    }
}
