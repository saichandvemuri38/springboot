package com.example.Apache_kafka.controller;

import com.example.Apache_kafka.services.CabLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/location")
public class CabLocationController {
    @Autowired
    private CabLocationService cabLocationService;

    @PutMapping
    public ResponseEntity updateLocation() throws InterruptedException {
        int range = 100;
        while (range > 0) {
            cabLocationService.updateCabLocation(Math.random() + " " + Math.round(Math.random() * range));
            Thread.sleep(1000);
            range--;
        }
        return new ResponseEntity<>(Map.of("message", "Location of driver"), HttpStatus.OK);
    }
}
