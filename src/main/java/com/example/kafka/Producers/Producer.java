package com.example.kafka.Producers;

import com.example.kafka.models.*;
import com.example.kafka.utils.DataGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public <T> void sendMessage(T message, String topic) {
        if(message instanceof UserClicks) {
            this.produce(DataGenerator.generate(UserClicks.class), topic);
        } else if (message instanceof UserLogins) {
            this.produce(DataGenerator.generate(UserLogins.class), topic);
        } else if (message instanceof UserPurchases) {
            this.produce(DataGenerator.generate(UserPurchases.class), topic);
        } else if (message instanceof UserReviews) {
            this.produce(DataGenerator.generate(UserReviews.class), topic);
        } else if (message instanceof UserSearches) {
            this.produce(DataGenerator.generate(UserSearches.class), topic);
        }
    }

    public <T> void produce(T message, String topic) {
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable ex) {
                logger.error("Unable to send message=[{}] due to: {}", message, ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                logger.info("Sent message=[{}] with offset=[{}]", message, result.getRecordMetadata().offset());
            }
        });
    }
}
