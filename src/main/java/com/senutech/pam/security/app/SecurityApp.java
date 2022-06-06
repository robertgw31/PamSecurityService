package com.senutech.pam.security.app;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication
public class SecurityApp {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SecurityApp.class);
        //app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}
