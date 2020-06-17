package org.example.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping(value = "/")
    public String hello() {
        return "hello"; // return /WEB-INF/hello.jsp
    }
}