package com.example.startspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.startspring")
public class StartspringApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartspringApplication.class, args);
    }
}
