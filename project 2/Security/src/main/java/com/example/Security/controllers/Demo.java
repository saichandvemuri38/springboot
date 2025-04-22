package com.example.Security.controllers;

import com.example.Security.entity.User;
import com.example.Security.model.AuthenticationResponse;
import com.example.Security.service.AuthService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth/demo")
public class Demo {
    @Autowired
    public AuthService authService;
    @GetMapping
    public ResponseEntity<List<User>> demo() {

        return ResponseEntity.ok(authService.getAllUsers());
    }
}
