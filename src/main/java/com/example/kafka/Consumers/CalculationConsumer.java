package com.example.kafka.Consumers;

import com.example.kafka.Topologies.CalculationTopology;
import org.apache.kafka.streams.StreamsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CalculationConsumer {
    private final Logger logger = LoggerFactory.getLogger(CalculationConsumer.class);

//    @KafkaListener(id = "calculation", topics = "calculation", groupId = "calculation-0")
    @Autowired
    public void consume(StreamsBuilder streamsBuilder) {
        CalculationTopology
                .build(streamsBuilder);
    }
}
