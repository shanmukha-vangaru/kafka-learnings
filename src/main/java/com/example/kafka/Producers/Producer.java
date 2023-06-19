package com.example.kafka.Producers;

import com.example.kafka.models.Calculation;
import com.example.kafka.models.User;
import com.example.kafka.Utils.DataGenerator;
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
        if (message instanceof Calculation) {
            Calculation calculation = DataGenerator.getCalculation();
            this.produce(calculation, topic, calculation.getRequestId());
        } else if (message instanceof User) {
            User user = DataGenerator.getUser();
            this.produce(user, topic, String.valueOf(user.getUserId()));
        }
    }

    public void produce(Object message, String topic, String key) {
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, key, message);
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
