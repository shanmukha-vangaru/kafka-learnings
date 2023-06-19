package com.example.kafka.Processor;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

import static org.apache.kafka.streams.StreamsConfig.APPLICATION_ID_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.BOOTSTRAP_SERVERS_CONFIG;

@Service
public class StreamProcessor {

    private KafkaStreams kafkaStreams;

    @PostConstruct
    public void init() {
        StreamsConfig properties = new StreamsConfig(
                Map.of(
                APPLICATION_ID_CONFIG, "spring-boot-kafka",
                BOOTSTRAP_SERVERS_CONFIG, "pkc-6ojv2.us-west4.gcp.confluent.cloud:9092")
        );
//        kafkaStreams = new KafkaStreams(CalculationTopology.build(new StreamsBuilder()), properties);
//        kafkaStreams.start();

//        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//            if (null != kafkaStreams) {
//                kafkaStreams.close();
//            }
//        }));
    }

    public KafkaStreams getKafkaStreams() {
        return kafkaStreams;
    }

    public void setKafkaStreams(KafkaStreams kafkaStreams) {
        this.kafkaStreams = kafkaStreams;
    }
}
