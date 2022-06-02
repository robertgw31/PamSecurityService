package com.senutech.pam.security.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class SecurityApp {

    public static void main(String args[]) {

        System.out.println("Listening on port " + System.getProperty("server.port"));
        SpringApplication.run(SecurityApp.class, args);
    }
}
