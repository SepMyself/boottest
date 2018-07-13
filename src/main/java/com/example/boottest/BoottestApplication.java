package com.example.boottest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.example.boottest")
@SpringBootApplication
public class BoottestApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoottestApplication.class, args);
    }
}
