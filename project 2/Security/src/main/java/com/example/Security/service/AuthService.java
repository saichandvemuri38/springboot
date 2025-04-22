package com.example.Security.service;

import com.example.Security.entity.User;
import com.example.Security.model.AuthenticationRequest;
import com.example.Security.model.AuthenticationResponse;
import com.example.Security.model.RegisterRequest;
import com.example.Security.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService{
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        System.out.println("Authentication Successful");
        var user = userRepo.findByEmail(request.getEmail()).orElse(null);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var user = User.builder().name(registerRequest.getName()).password(passwordEncoder.encode(registerRequest.getPassword())).role(registerRequest.getRole()).email(registerRequest.getEmail()).build();
        userRepo.save(user);
        System.out.println("Registration Successful");
        var jwtToken = jwtService.generateToken(user);
        System.out.println(jwtToken);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

}
