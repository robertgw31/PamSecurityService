package com.senutech.pam.app.service;


import com.senutech.pam.security.app.SecurityApp;
import com.senutech.pam.security.app.model.domain.Userlogin;
import com.senutech.pam.security.app.service.EmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes= SecurityApp.class)
public class EmailServiceTester {

    @Autowired
    private EmailService emailService;

    @Test
    public void testEmailSend() {
        try {
            emailService.sendSimpleMessage("robertw@senutech.com", "Test Message", "Testing SMTP functionality");
        }catch(Exception e) {
            e.printStackTrace(System.err);
        }
    }

    @Test
    public void testTemplateEmail() {
        try {
            Userlogin user = new Userlogin();
            String userFullName = "Robert Wittnebert";
            String userEmail = "robertw@senutech.com";
            String verificationUrl = "http://www.cnn.com";
            emailService.sendEmailConfirmationMessage(userFullName,userEmail,verificationUrl);

        }catch(Exception e) {
            e.printStackTrace(System.err);
        }
    }
}

