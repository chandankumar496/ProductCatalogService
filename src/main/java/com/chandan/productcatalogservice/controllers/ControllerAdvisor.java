package com.chandan.productcatalogservice.controllers;


import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
    public String handleException(Exception e) {
        //e.printStackTrace();
        System.out.println("This is my exception handler");
        return e.getMessage();
    }
}
