package com.senutech.security.app.controller
        ;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @RequestMapping("/s/hello-world")
    public String getWelcomeMessage() {

        return "Hello World...!!!";
    }
}
