package com.example.kafka.Serializers;

import com.example.kafka.models.Calculation;
import com.example.kafka.models.Result;
import com.example.kafka.models.ResultWithUser;
import com.example.kafka.models.User;
import io.confluent.kafka.streams.serdes.protobuf.KafkaProtobufSerde;

import java.util.Map;

import static io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig.*;

public class ProtobufSerde {

    public static final Map<String, String> serdeConfig =
            Map.of(SCHEMA_REGISTRY_URL_CONFIG, "https://psrc-vn38j.us-east-2.aws.confluent.cloud",
                    BASIC_AUTH_CREDENTIALS_SOURCE, "USER_INFO",
                    USER_INFO_CONFIG, "TOPHK74AHLRE253S:+xn+ar5fIRNstSweO6W4DxMhI3ymZ3ynEbDkk2X8IRmjlR1KPiC33fObIvLNJp1T");

    public static KafkaProtobufSerde<Calculation> calculationSerde() {
        final KafkaProtobufSerde<Calculation> protobufSerde = new KafkaProtobufSerde<>(Calculation.class);
        protobufSerde.configure(serdeConfig, false);
        return protobufSerde;
    }

    public static KafkaProtobufSerde<User> userSerde() {
        final KafkaProtobufSerde<User> protobufSerde = new KafkaProtobufSerde<>(User.class);
        protobufSerde.configure(serdeConfig, false);
        return protobufSerde;
    }

    public static KafkaProtobufSerde<Result> resultSerde() {
        final KafkaProtobufSerde<Result> protobufSerde = new KafkaProtobufSerde<>(Result.class);
        protobufSerde.configure(serdeConfig, false);
        return protobufSerde;
    }

    public static KafkaProtobufSerde<ResultWithUser> resultWithUserSerde() {
        final KafkaProtobufSerde<ResultWithUser> protobufSerde = new KafkaProtobufSerde<>(ResultWithUser.class);
        protobufSerde.configure(serdeConfig, false);
        return protobufSerde;
    }
}
