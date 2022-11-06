package com.example.guestbookdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/sample")
    public String[] hello() {
        return new String[]{"Hello", "World"};
    }
}
