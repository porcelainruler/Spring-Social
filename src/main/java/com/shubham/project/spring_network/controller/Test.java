package com.shubham.project.spring_network.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Test Group", description = "A group of testing apis")
@RestController
public class Test {
    @GetMapping("/test")
    public String getHello () {
        return "Hello There !!!";
    }
}
