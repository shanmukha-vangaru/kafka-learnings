package com.example.kafka.Calculations;

import com.example.kafka.models.Operator;

public class Calculator {
    public static float calculate(Operator operator, float param1, float param2) {
        switch (operator) {
            case ADDITION:
                return param1 + param2;
            case MULTIPLICATION:
                return param1 * param2;
            case SUBTRACTION:
                return param1 - param2;
            case DIVISION:
                return param1 / param2;
            default:
                return 0;
        }
    }
}
