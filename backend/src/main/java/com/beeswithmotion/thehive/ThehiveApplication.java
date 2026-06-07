package com.beeswithmotion.thehive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ThehiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThehiveApplication.class, args);
        System.out.println("Welcome to the Hive.");
    }

}
