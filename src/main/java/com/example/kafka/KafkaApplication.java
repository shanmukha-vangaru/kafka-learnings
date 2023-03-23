package com.example.kafka;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ContainerProperties;

@SpringBootApplication
//@EnableKafkaStreams
public class KafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaApplication.class, args);
	}

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory kafkaListenerContainerFactory(
			ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
			ConsumerFactory<Object, Object> consumerFactory,
			KafkaTemplate<Object, Object> kafkaTemplate
	) {
		ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
		configurer.configure(factory, consumerFactory);
		// retry in blocking-way
//		factory.setCommonErrorHandler(new DefaultErrorHandler((consumerRecord, exception) -> {
//			kafkaTemplate.send(consumerRecord.topic() + ".DLD", consumerRecord.value());
//		}, new FixedBackOff(10, 2)));
		factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.RECORD);
		factory.afterPropertiesSet();
		return factory;
	}

}
