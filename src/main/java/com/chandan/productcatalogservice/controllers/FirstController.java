package com.chandan.productcatalogservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

    @GetMapping
    public ResponseEntity<String> welcome(){
        return ResponseEntity.ok("Welcome to First Controller");
    }
}
