package com.example.kafka.Consumers;

import com.example.kafka.mappers.UserReviewsMapper;
import com.example.kafka.models.UserReviews;
import com.example.kafka.respository.UserReviewsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserReviewsConsumer {
    private final Logger logger = LoggerFactory.getLogger(UserReviewsConsumer.class);

    @Autowired
    private UserReviewsRepository userReviewsRepository;

    @KafkaListener(id = "user-reviews", topics = "user-reviews", groupId = "user-reviews-0")
    public void consume(UserReviews userReviews) {
        logger.info("User Reviews: " + userReviews.toString());
        userReviewsRepository.save(UserReviewsMapper.getEntity(userReviews));
    }
}
