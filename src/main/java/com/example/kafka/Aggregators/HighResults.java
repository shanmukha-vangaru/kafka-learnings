package com.example.kafka.Aggregators;

import com.example.kafka.models.ResultWithUser;

import java.util.ArrayList;
import java.util.List;

public class HighResults {

//    private final TreeSet<ResultWithUser> highResults = new TreeSet<>(new ResultWithUserComparator());

    private final List<ResultWithUser> highResults = new ArrayList<>();

    public HighResults add(final ResultWithUser resultWithUser) {
        highResults.add(resultWithUser);
        System.out.println("intermediate result: " + highResults);
//        if (highResults.size() > 3) {
//            highResults.remove(highResults.last());
//        }

        return this;
    }

    public List<ResultWithUser> toList() {

//        Iterator<ResultWithUser> results = highResults.iterator();
//        List<ResultWithUser> highResults = new ArrayList<>();
//        while (results.hasNext()) {
//            System.out.println("intermediate result: " + results.toString());
//            highResults.add(results.next());
//        }
        return highResults;

    }

}
