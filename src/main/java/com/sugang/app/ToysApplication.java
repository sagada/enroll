package com.sugang.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class ToysApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToysApplication.class, args);
    }

}
