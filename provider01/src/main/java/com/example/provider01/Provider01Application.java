package com.example.provider01;

import jakarta.annotation.Resource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class Provider01Application {
    public static void main(String[] args) {
        SpringApplication.run(Provider01Application.class, args);
    }
}
