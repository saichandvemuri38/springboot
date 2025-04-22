package com.newsletter.auth_service.controller;

import com.newsletter.auth_service.enity.UserEntity;
import com.newsletter.auth_service.interfaces.UserInterface;
import com.newsletter.auth_service.models.LoginModel;
import com.newsletter.auth_service.models.LoginResponseDTO;
import com.newsletter.auth_service.models.UserModel;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserInterface userInterface;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginModel loginModel) {
        String token = userInterface.login(loginModel);
        if (Objects.equals(token, "error")) {
            return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponseDTO> register(@RequestBody UserModel user) {
        String token = userInterface.register(user);
        if (Objects.equals(token, "error")) {
            return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
    @GetMapping("/validate")
    public ResponseEntity<Void> validateToken(@RequestHeader("Authorization") String bearertoken) {
        if(bearertoken == null || !bearertoken.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED).build();
        }
        return userInterface.validateToken(bearertoken.substring(7)) ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED).build();
    }
}
