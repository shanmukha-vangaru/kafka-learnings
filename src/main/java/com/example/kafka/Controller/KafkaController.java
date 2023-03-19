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

    @PostMapping(value = "/produce/user-clicks")
    public String produceUserClicks(@RequestParam String requestSize) {
        int numberOfUserClicks = Integer.parseInt(requestSize);
        for(int i = 0; i < numberOfUserClicks; i++)
            producer.sendMessage(new UserClicks(), "user-clicks");
        return "success";
    }

    @PostMapping(value = "/produce/user-logins")
    public String produceUserLogins(@RequestParam String requestSize) {
        int numberOfUserLogins = Integer.parseInt(requestSize);
        for(int i = 0; i < numberOfUserLogins; i++)
            producer.sendMessage(new UserLogins(), "user-logins");
        return "success";
    }

    @PostMapping(value = "/produce/user-purchases")
    public String produceUserPurchases(@RequestParam String requestSize) {
        int numberOfUserPurchases = Integer.parseInt(requestSize);
        for(int i = 0; i < numberOfUserPurchases; i++)
            producer.sendMessage(new UserPurchases(), "user-purchases");
        return "success";
    }

    @PostMapping(value = "/produce/user-reviews")
    public String produceUserReviews(@RequestParam String requestSize) {
        int numberOfUserReviews = Integer.parseInt(requestSize);
        for(int i = 0; i < numberOfUserReviews; i++)
            producer.sendMessage(new UserReviews(), "user-reviews");
        return "success";
    }

    @PostMapping(value = "/produce/user-searches")
    public String produceUserSearches(@RequestParam String requestSize) {
        int numberOfUserSearches = Integer.parseInt(requestSize);
        for(int i = 0; i < numberOfUserSearches; i++)
            producer.sendMessage(new UserSearches(), "user-searches");
        return "success";
    }
}
