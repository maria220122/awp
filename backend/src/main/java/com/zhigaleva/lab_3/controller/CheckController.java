package com.zhigaleva.lab_3.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
public class CheckController {

    @GetMapping("/check")
    public HttpStatus check() {
        return HttpStatus.OK;
    }
}
