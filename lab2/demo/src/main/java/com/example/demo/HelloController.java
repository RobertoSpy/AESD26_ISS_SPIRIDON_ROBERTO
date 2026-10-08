package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    // Preia valoarea 'app.greeting' din application.properties
    @Value("${app.greeting}")
    private String greetingMessage;

    @GetMapping("/hello")
    public String sayHello() {
        return greetingMessage;
    }
}
