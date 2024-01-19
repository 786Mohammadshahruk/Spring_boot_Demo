package com.sthumbh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class RequestController {

    @GetMapping //API
    public String getHelloWorld() {
        return "Hello World";
    }


    @PostMapping(value = "/createEmployee")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employee;
    }

}
