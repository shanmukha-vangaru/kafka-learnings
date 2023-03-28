package com.example.kafka.utils;

import com.example.kafka.models.*;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import java.lang.reflect.Field;

public class DataGenerator {

    public static PhoneNumber generatePhoneNumber(EasyRandom generator) {
        return PhoneNumber.newBuilder()
                .setNumber(generator.nextObject(String.class))
                .setPhoneNumberType(PhoneNumberType.MOBILE)
                .build();
    }

    public static Address generateAddress(EasyRandom generator) {
        return Address.newBuilder()
                .setDoorNumber(generator.nextObject(String.class))
                .setStreet(generator.nextObject(String.class))
                .setLandmark(generator.nextObject(String.class))
                .setCity(generator.nextObject(String.class))
                .setState(generator.nextObject(String.class))
                .setZipcode(generator.nextInt())
                .setCountry(generator.nextObject(String.class))
                .setLatitude(generator.nextDouble())
                .setLongitude(generator.nextDouble())
                .setAddressType(AddressType.CURRENT_ADDRESS)
                .build();
    }

    public static User generateUser() {
        EasyRandomParameters randomParameters = new EasyRandomParameters();
        randomParameters.excludeField(Field::isEnumConstant);
        randomParameters.seed(System.currentTimeMillis());
        EasyRandom generator = new EasyRandom(randomParameters);
        return User.newBuilder()
                .setUserId(generator.nextInt())
                .setFirstName(generator.nextObject(String.class))
                .setLastName(generator.nextObject(String.class))
                .setUserType(UserType.ELITE)
                .addHobbies(generator.nextObject(String.class))
                .addHobbies(generator.nextObject(String.class))
                .setIsActive(generator.nextBoolean())
                .setSalary(generator.nextDouble())
                .setEmail(generator.nextObject(String.class))
                .addAddress(generateAddress(generator))
                .addAddress(generateAddress(generator))
                .addPhoneNumber(generatePhoneNumber(generator))
                .addPhoneNumber(generatePhoneNumber(generator))
                .build();
    }
}
