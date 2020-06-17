package org.example.mvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    @RequestMapping("/hello")
    public String sayHello() {
        return "Hello, world";
    }
}
