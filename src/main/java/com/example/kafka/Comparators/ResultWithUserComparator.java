package com.example.kafka.Comparators;

import com.example.kafka.models.ResultWithUser;

import java.util.Comparator;

public class ResultWithUserComparator implements Comparator<ResultWithUser> {
    @Override
    public int compare(ResultWithUser o1, ResultWithUser o2) {
        return (int) (o1.getResult() - o2.getResult());
    }
}
