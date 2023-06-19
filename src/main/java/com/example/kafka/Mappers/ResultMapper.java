package com.example.kafka.Mappers;

import com.example.kafka.models.Calculation;
import com.example.kafka.models.Result;

public class ResultMapper {
    public static Result getResult(Calculation request, Float result) {
        return Result.newBuilder()
                .setRequest(request)
                .setResult(result)
                .build();
    }
}
