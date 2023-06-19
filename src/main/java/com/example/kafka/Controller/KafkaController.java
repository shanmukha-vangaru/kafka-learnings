package com.example.kafka.Controller;

import com.example.kafka.Aggregators.HighResults;
import com.example.kafka.Producers.Producer;
import com.example.kafka.models.Calculation;
import com.example.kafka.models.ResultWithUser;
import com.example.kafka.models.User;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyQueryMetadata;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class KafkaController {

    @Autowired
    Producer producer;

    @Autowired
    StreamsBuilderFactoryBean factoryBean;

    @PostMapping(value = "/produce/calculation")
    public String produceCalculations(@RequestParam String requestSize) {
        int numberOfCalculations = Integer.parseInt(requestSize);
        for(int i = 0; i < numberOfCalculations; i++)
            producer.sendMessage(Calculation.newBuilder().build(), "calculation");
        return "success";
    }

    @PostMapping(value = "/produce/users")
    public String produceUsers(@RequestParam String requestSize) {
        int numberOfUsers = Integer.parseInt(requestSize);
        for(int i = 0; i < numberOfUsers; i++)
            producer.sendMessage(User.newBuilder().build(), "user");
        return "success";
    }

    @GetMapping(value = "/{storeName}/{key}")
    public ResponseEntity<List<ResultWithUser>> getHighResults(
            @PathVariable("storeName") String storeName,
            @PathVariable("key") String key
    ) {
        KafkaStreams streams = factoryBean.getKafkaStreams();
        ReadOnlyKeyValueStore<String, HighResults> keyValueStore =
                streams.store(
                        StoreQueryParameters.fromNameAndType(
                                "high-results",
                                QueryableStoreTypes.keyValueStore()
                        )
                );
//        StreamsConfig
        KeyQueryMetadata metadata = streams.queryMetadataForKey("high-results", key, Serdes.String().serializer());
//        while (keyValueStore.all().hasNext()) {
//            System.out.println("xyz: " + keyValueStore.all().next().toString());
//        }
        System.out.println("results: " + keyValueStore.approximateNumEntries());
        return ResponseEntity.ok(keyValueStore.get(key).toList());
    }

}
