package com.example.autogaragebackend;

import com.example.autogaragebackend.enums.Role;
import com.example.autogaragebackend.model.User;
import com.example.autogaragebackend.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AutogaragebackendApplication {

    UserRepository userRepository;


    public static void main(String[] args) {
        SpringApplication.run(AutogaragebackendApplication.class, args);

    }



}
