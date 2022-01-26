package com.your_help.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
public class YourHelpApplication {

    public static void main(String[] args) {
        SpringApplication.run(YourHelpApplication.class, args);
    }

}
