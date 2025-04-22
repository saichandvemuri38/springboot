package com.example.springbootdemo.controllers;

import com.example.springbootdemo.model.Address;
import com.example.springbootdemo.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "Hello World";
    }

    @GetMapping("/user")
    public User user() {
        User user = new User();
        Address address = new Address();
        address.setCity("1");
        user.setId("1");
        user.setAddress(address);
        return user;
    }

    @GetMapping("/{id}/{id2}")
    public String pathVariable(@PathVariable String id, @PathVariable("id2") String name) {
        return "Hello World" + id + ":" + name;
    }

    @GetMapping("/param")
    public String reqParam(@RequestParam String id, @RequestParam(name = "fullname", required = false, defaultValue = "") String name) {
        return "Hello World" + id + ":" + name;
    }
}
