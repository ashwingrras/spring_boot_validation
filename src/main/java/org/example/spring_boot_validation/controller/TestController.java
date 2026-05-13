package org.example.spring_boot_validation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String testMethod()
    {
        return "spring boot root";
    }
}
