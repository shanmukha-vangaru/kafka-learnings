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
    private KafkaTemplate<String, User> kafkaTemplate;

    public <T> void sendMessage(T message, String topic) {
        if (message instanceof User) {
            this.produce(DataGenerator.generateUser(), topic);
        }
    }

    public void produce(User message, String topic) {
        ListenableFuture<SendResult<String, User>> future = kafkaTemplate.send(topic, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, User>>() {
            @Override
            public void onFailure(Throwable ex) {
                logger.error("Unable to send message=[{}] due to: {}", message, ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, User> result) {
                logger.info("Sent message=[{}] with offset=[{}]", message, result.getRecordMetadata().offset());
            }
        });
    }
}
