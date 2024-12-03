package com.stroygen.urdisauthservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class UrdisAuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UrdisAuthServiceApplication.class, args);
    }

}
