package com.example.kafka;

import org.apache.kafka.streams.StreamsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication
@EnableKafkaStreams
@EnableKafka
public class KafkaApplication {

//	@Value(value = "${spring.kafka.bootstrap-servers}")
//	private String bootstrapAddress;
//
//	@Value(value = "${spring.kafka.streams.application-id}")
//	private String applicationId;

//	@Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
//	KafkaStreamsConfiguration kafkaStreamsConfiguration() {
//		Map<String, Object> props = new HashMap<>();
//		props.put(APPLICATION_ID_CONFIG, "spring-boot-kafka");
//		props.put(BOOTSTRAP_SERVERS_CONFIG, "pkc-6ojv2.us-west4.gcp.confluent.cloud:9092");
//		props.put(DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
//		props.put(DEFAULT_VALUE_SERDE_CLASS_CONFIG, KafkaProtobufSerde.class);
//		return new KafkaStreamsConfiguration(props);
//	}

	@Bean
	public StreamsConfig streamsConfig(KafkaProperties kafkaProperties) {
		return new StreamsConfig(kafkaProperties.buildStreamsProperties());
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaApplication.class, args);
	}

}
