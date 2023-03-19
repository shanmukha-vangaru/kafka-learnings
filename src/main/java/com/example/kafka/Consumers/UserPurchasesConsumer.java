package com.example.kafka.Consumers;

import com.example.kafka.mappers.UserPurchasesMapper;
import com.example.kafka.models.UserPurchases;
import com.example.kafka.respository.UserPurchasesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserPurchasesConsumer {
    private final Logger logger = LoggerFactory.getLogger(UserPurchasesConsumer.class);

    @Autowired
    private UserPurchasesRepository userPurchasesRepository;

    @KafkaListener(id = "user-purchases", topics = "user-purchases", groupId = "user-purchases-0")
    public void consume(UserPurchases userPurchases) {
        logger.info("User Purchases: " + userPurchases.toString());
        userPurchasesRepository.save(UserPurchasesMapper.getEntity(userPurchases));
    }
}
