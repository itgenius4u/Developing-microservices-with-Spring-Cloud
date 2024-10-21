package com.example.myapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloService {

    @GetMapping("/hello")
    public String helloworld() {
        return "Spring Boot HelloWorld";
    }
    
}

