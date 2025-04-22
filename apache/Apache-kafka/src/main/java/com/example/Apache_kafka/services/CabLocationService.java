package com.example.Apache_kafka.services;

import com.example.Apache_kafka.constant.AppConstant;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CabLocationService {
    @Autowired
    private KafkaTemplate<String, Object> producer;

    public boolean updateCabLocation(String cabLocation) {
        System.out.println(cabLocation);
        producer.send(AppConstant.CAB_LOCATION, cabLocation);
        return true;
    }
}
