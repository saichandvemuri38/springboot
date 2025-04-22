package com.example.libraryService.controllers;

import com.example.libraryService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class Librarycontroller {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }
}
