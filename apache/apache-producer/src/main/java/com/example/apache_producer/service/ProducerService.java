package com.example.apache_producer.service;

import com.example.apache_producer.DTO.Customer;
import com.example.apache_producer.constant.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ProducerService {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

//    public void sendMessageToTopic(String message) {
//        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(Topic.TOPIC, message);
//        future.whenComplete((result, ex) -> {
//            if (ex == null) {
//                System.out.println("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]"+ result.getRecordMetadata().partition());
//            } else {
//                System.err.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
//            }
//        });
//    }

    public void sendEventsToTopic(Customer customer) {
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(Topic.CUSTOMER_TOPIC, customer);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + customer.toString() + "] with offset=[" + result.getRecordMetadata().offset() + "]"+ result.getRecordMetadata().partition());
            } else {
                System.err.println("Unable to send message=[" + customer.toString() + "] due to : " + ex.getMessage());
            }
        });
    }
}
