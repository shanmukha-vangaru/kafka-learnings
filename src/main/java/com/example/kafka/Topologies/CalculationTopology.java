package com.example.kafka.Topologies;

import com.example.kafka.Aggregators.HighResults;
import com.example.kafka.Calculations.Calculator;
import com.example.kafka.Mappers.ResultMapper;
import com.example.kafka.Serializers.JsonDeserializer;
import com.example.kafka.Serializers.JsonSerializer;
import com.example.kafka.Serializers.ProtobufSerde;
import com.example.kafka.models.*;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;

public class CalculationTopology {

    private static Predicate<String, Calculation> getPredicate(Operator operator) {
        return (key, value) -> value.getOperator().toString().equals(operator.toString());
    }

    private static KStream<String, Result> getResultStream(KStream<String, Calculation> calculationStream) {
        return calculationStream.map((key, value) -> {
            String userId = String.valueOf(value.getUserId());
            Result result = ResultMapper.getResult(value,
                    Calculator.calculate(value.getOperator(), value.getOperand1(), value.getOperand2()));
            return KeyValue.pair(userId, result);
        });
//        return calculationStream.mapValues(calculation ->
//                ResultMapper.getResult(calculation, Calculator.calculate(calculation.getOperator(), calculation.getOperand1(), calculation.getOperand2())));
    }

    public static Topology build(StreamsBuilder builder) {

        // register stream on calculation topic
        KStream<String, Calculation> stream =
                builder.stream("calculation",
                        Consumed.with(Serdes.String(), ProtobufSerde.calculationSerde()));

        // register ktable on users topic
        /**
         * 1. Used when the key set is large
         * 2. Need time synchronized response
         */
        KTable<String, User> users =
                builder.table("user",
                        Consumed.with(Serdes.String(), ProtobufSerde.userSerde()));

        // print the stream ----- ONLY FOR DEBUGGING ------
//        stream.print(Printed.<String, Calculation>toSysOut().withLabel("calculations"));

        // predicate for addition operations
        Predicate<String, Calculation> additionPredicate = getPredicate(Operator.ADDITION);

//        // predicate for multiplication operations
        Predicate<String, Calculation> multiplicationPredicate = getPredicate(Operator.MULTIPLICATION);
//
//        // predicate for subtraction operations
        Predicate<String, Calculation> subtractionPredicate = getPredicate(Operator.SUBTRACTION);
//
//        // predicate for division operations
        Predicate<String, Calculation> divisionPredicate = getPredicate(Operator.DIVISION);

        // branch based on type of operator
        KStream<String, Calculation>[] branches = stream.branch(additionPredicate, multiplicationPredicate, subtractionPredicate, divisionPredicate);

        // addition requests
        KStream<String, Calculation> additionStream = branches[0];
        // ----- ONLY FOR DEBUGGING ------
//        additionStream.print(Printed.<String, Calculation>toSysOut().withLabel("addition-calculations"));

        // multiplication requests
        KStream<String, Calculation> multiplicationStream = branches[1];
        // ----- ONLY FOR DEBUGGING ------
//        multiplicationStream.print(Printed.<String, Calculation>toSysOut().withLabel("multiplication-calculations"));

        // subtraction requests
        KStream<String, Calculation> subtractionStream = branches[2];
        // ----- ONLY FOR DEBUGGING ------
//        subtractionStream.print(Printed.<String, Calculation>toSysOut().withLabel("subtraction-calculations"));

        // division requests
        KStream<String, Calculation> divisionStream = branches[3];
        // ----- ONLY FOR DEBUGGING ------
//        divisionStream.print(Printed.<String, Calculation>toSysOut().withLabel("division-calculations"));

        // addition calculations
        KStream<String, Result> additionResultStream = getResultStream(additionStream);
        // ----- ONLY FOR DEBUGGING ------
//        additionResultStream.print(Printed.<String, Result>toSysOut().withLabel("addition-calculations"));

        // multiplication calculations
        KStream<String, Result> multiplicationResultStream = getResultStream(multiplicationStream);
        // ----- ONLY FOR DEBUGGING ------
//        multiplicationResultStream.print(Printed.<String, Result>toSysOut().withLabel("multiplication-calculations"));

        // subtraction calculations
        KStream<String, Result> subtractionResultStream = getResultStream(subtractionStream);
        // ----- ONLY FOR DEBUGGING ------
//        subtractionResultStream.print(Printed.<String, Result>toSysOut().withLabel("subtraction-calculations"));

        // subtraction calculations
        KStream<String, Result> divisionResultStream = getResultStream(divisionStream);
        // ----- ONLY FOR DEBUGGING ------
//        divisionResultStream.print(Printed.<String, Result>toSysOut().withLabel("division-calculations"));

        // merge different result streams
        KStream<String, Result> intermediateStream1 = additionResultStream.merge(multiplicationResultStream);

        KStream<String, Result> intermediateStream2 = intermediateStream1.merge(subtractionResultStream);

        KStream<String, Result> allResults = intermediateStream2.merge(divisionResultStream);

        // ----- ONLY FOR DEBUGGING ------
//        allResults.print(Printed.<String, Result>toSysOut().withLabel("all-calculations"));

        // flatMap <new key, new values>, flatMapValues <old key, new values> are used when we want to transform one value into multiple values

        ValueJoiner<Result, User, ResultWithUser> resultUserJoiner =
                (result, user) -> ResultWithUser.newBuilder()
                        .setUserId(user.getUserId())
                        .setRequestId(result.getRequest().getRequestId())
                        .setOperator(result.getRequest().getOperator())
                        .setResult(result.getResult())
                        .setName(user.getFirstName() + " " + user.getLastName())
                        .build();

        Joined<String, Result, User> resultUserJoinParams =
                Joined.with(Serdes.String(),
                        ProtobufSerde.resultSerde(),
                        ProtobufSerde.userSerde());

        KStream<String, ResultWithUser> resultWithUserKStream =
                allResults.join(
                        users,
                        resultUserJoiner,
                        resultUserJoinParams);

        // ----- ONLY FOR DEBUGGING ------
        resultWithUserKStream.print(Printed.<String, ResultWithUser>toSysOut().withLabel("result-user"));

        // Grouping streams
        /**
         * Return type - KGroupedStreams
         * 1. groupBy - generates new key and groups the messages based on that new key.
         * 2. groupByKey - groups the messages based on the existing key
         */

        KGroupedStream<String, ResultWithUser> groupedStream =
                resultWithUserKStream.groupByKey(
                        Grouped.with(Serdes.String(),
                                ProtobufSerde.resultWithUserSerde())
                );

        // Grouping KTables
        /**
         * Return type - KGroupedTables
         * 1. groupBy
         */

        // Aggregations
        /**
         * 1. aggregate - result stream can of different type than that of input
         * 2. reduce - input and the result stream should be of same type
         * 3. count - number of events per key
         */

        // Aggregate streams
        /* Initializer */
        Initializer<HighResults> highResultsInitializer = HighResults::new;

        /* Adder */
        Aggregator<String, ResultWithUser, HighResults> highResultsAggregator =
                ((key, value, aggregate) -> aggregate.add(value));

        /* Aggregate */
        KTable<String, HighResults> highResultsKTable =
                groupedStream.aggregate(
                        highResultsInitializer, highResultsAggregator,
                        Materialized.<String, HighResults, KeyValueStore<Bytes, byte[]>>
                        as("high-results")
                .withKeySerde(Serdes.String())
                .withValueSerde(Serdes.serdeFrom(new JsonSerializer<>(), new JsonDeserializer<>(HighResults.class))));

        /**
         * To query the output of the above aggregator, we need to expose read-only mode
         * to the internal state store. This can be done using the materialized stores.
         */

        /**
         * QueryableStoreTypes.keyValueStore()
         * QueryableStoreTypes.timestampedKeyValueStore()
         * QueryableStoreTypes.windowStore()
         * QueryableStoreTypes.timestampedWindowStore()
         * QueryableStoreTypes.sessionStore()
         */



        return builder.build();
    }
}
