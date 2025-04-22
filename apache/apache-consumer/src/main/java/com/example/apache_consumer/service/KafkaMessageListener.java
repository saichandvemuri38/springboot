package com.example.apache_consumer.service;


import com.example.apache_consumer.DTO.Customer;
import com.example.apache_consumer.constant.Topic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageListener {

    Logger logger = LoggerFactory.getLogger(KafkaMessageListener.class);

    //    @KafkaListener(topics = "topic1", groupId = "consumer-group")
//    public void consume(String message) {
//        logger.info("Consumer0 Consume the message "+message);
//    }
//    @KafkaListener(topics = "topic1", groupId = "consumer-group")
//    public void consume1(String message) {
//        logger.info("Consumer1 Consume the message "+message);
//    }
//    @KafkaListener(topics = "topic1", groupId = "consumer-group")
//    public void consume2(String message) {
//        logger.info("Consumer2 Consume the message "+message);
//    }
//    @KafkaListener(topics = "topic1", groupId = "consumer-group")
//    public void consume3(String message) {
//        logger.info("Consumer3 Consume the message "+message);
//    }
    @KafkaListener(topics = Topic.CUSTOMER_TOPIC, groupId = "consumer-group1")
    public void consume(Customer customer) {
        logger.info("Consumer received: " + customer);
    }
}
