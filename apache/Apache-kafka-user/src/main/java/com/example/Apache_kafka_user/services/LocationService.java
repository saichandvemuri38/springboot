package com.example.Apache_kafka_user.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
    @KafkaListener(topics = "topic", groupId = "consumer-group")
    public void cabLocation(String location) {
        System.out.println(location);
    }
}
