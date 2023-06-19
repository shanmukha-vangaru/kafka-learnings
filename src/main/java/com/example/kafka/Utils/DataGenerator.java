package com.example.kafka.Utils;

import com.example.kafka.models.Calculation;
import com.example.kafka.models.Operator;
import com.example.kafka.models.User;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import java.util.Random;

public class DataGenerator {
    public static Calculation getCalculation() {
        EasyRandomParameters randomParameters = new EasyRandomParameters();
        randomParameters.seed(System.currentTimeMillis());
        EasyRandom generator = new EasyRandom(randomParameters);
        return Calculation.newBuilder()
                .setRequestId(generator.nextObject(String.class))
                .setUserId(generator.nextInt(5))
                .setOperator(Operator.forNumber(new Random().nextInt(4)))
                .setOperand1(generator.nextFloat())
                .setOperand2(generator.nextFloat())
                .build();
    }

    public static User getUser() {
        EasyRandomParameters randomParameters = new EasyRandomParameters();
        randomParameters.seed(System.currentTimeMillis());
        EasyRandom generator = new EasyRandom(randomParameters);
        return User.newBuilder()
                .setFirstName(generator.nextObject(String.class))
                .setLastName(generator.nextObject(String.class))
                .setUserId(generator.nextInt(5))
                .build();
    }
}
