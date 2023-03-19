package com.example.kafka.utils;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import java.lang.reflect.Field;

public class DataGenerator {
    public static <T> T generate(Class<T> message) {
        EasyRandomParameters randomParameters = new EasyRandomParameters();
        randomParameters.excludeField(Field::isEnumConstant);
        randomParameters.seed(System.currentTimeMillis());
        EasyRandom generator = new EasyRandom(randomParameters);
        return generator.nextObject(message);
    }
}
