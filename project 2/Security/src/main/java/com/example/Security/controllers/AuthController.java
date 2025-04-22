package com.example.Security.controllers;

import com.example.Security.entity.User;
import com.example.Security.model.AuthenticationRequest;
import com.example.Security.model.AuthenticationResponse;
import com.example.Security.model.RegisterRequest;

import com.example.Security.service.AuthService;
import com.example.Security.service.UserInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> saveUser(@RequestBody RegisterRequest request) {
        System.out.println("RegisterRequest: " );
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @GetMapping()
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(authService.getAllUsers());
    }
}
