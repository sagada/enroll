package com.sugang.toys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class ToysApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToysApplication.class, args);
    }

}
