package com.example.apache_producer.controller;

import com.example.apache_producer.DTO.Customer;
import com.example.apache_producer.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer-app")
public class ProducerController {
    @Autowired
    private ProducerService producerService;

//    @GetMapping("/publish/{message}")
//    public ResponseEntity<String> publish(@PathVariable String message) {
//        try {
//            for (int i = 0; i <= 10000; i++) {
//                producerService.sendMessageToTopic(message + " : " + i);
//            }
//            producerService.sendMessageToTopic(message);
//            return ResponseEntity.ok("Message published");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }

    @PostMapping("/publish")
    public void sendEvents(@RequestBody Customer customer) {
        producerService.sendEventsToTopic(customer);
    }
}
