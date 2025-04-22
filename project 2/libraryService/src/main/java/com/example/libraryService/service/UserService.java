package com.example.libraryService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private RestTemplate restTemplate;
    @Cacheable("users")
    public List getUsers() {
        var users = restTemplate.getForObject("http://SPRING-SECURITY/auth", List.class);
        return List.of(users);
    }



}
