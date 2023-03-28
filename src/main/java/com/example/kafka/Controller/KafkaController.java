package com.example.kafka.Controller;

import com.example.kafka.Producers.Producer;
import com.example.kafka.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class KafkaController {

    @Autowired
    Producer producer;

    @PostMapping(value = "/produce/user")
    public String produceUsers(@RequestParam String requestSize) {
        int numberOfUsers = Integer.parseInt(requestSize);
        for(int i = 0; i < numberOfUsers; i++)
            producer.sendMessage(User.newBuilder().build(), "user");
        return "success";
    }
}
