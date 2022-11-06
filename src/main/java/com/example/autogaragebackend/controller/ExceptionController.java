package com.example.autogaragebackend.controller;

import com.example.autogaragebackend.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<Object> exception(ResourceNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }

}