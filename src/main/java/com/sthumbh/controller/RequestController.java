package com.sthumbh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {

    @GetMapping //API
    public String getHelloWorld() {
        return "Hello World";
    }

}
