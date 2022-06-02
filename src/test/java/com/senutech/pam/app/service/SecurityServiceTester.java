package com.senutech.pam.app.service;

import com.senutech.pam.security.app.SecurityApp;
import com.senutech.pam.security.app.model.containers.AccountCreateRequest;
import com.senutech.pam.security.app.model.containers.AccountCreateResult;
import com.senutech.pam.security.app.service.SecurityService;
import org.assertj.core.data.Offset;
import org.checkerframework.checker.fenum.qual.SwingTextOrientation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes= SecurityApp.class)
public class SecurityServiceTester {

    @Autowired
    private SecurityService securityService;

    @Test
    public void createAccount() {
        try {
            ZoneId zone = ZoneId.of("America/New_York");
            OffsetDateTime timestamp = OffsetDateTime.now(zone);
            String verificationUrl = "http://www.cnn.com";

            AccountCreateRequest request = new AccountCreateRequest();

            request.setAccountName("Robert Wittnebert");
            request.setIsoCountry("US");
            request.setIsoLanguage("EN");
            request.setLoginEmail("robertw@senutech.com");
            request.setLoginFullName("Robert Wittnebert");
            request.setLoginPassword("melissa");
            request.setOpenDateTime(timestamp);
            request.setRequestRecieptTime(timestamp);
            request.setClientMachine("10.10.10.1");
            request.setUserLocalDateTime(timestamp);
            request.setEmailVerificationUrlRoot(verificationUrl);
            AccountCreateResult res = securityService.createAccount(request);

        } catch(Exception e) {
            e.printStackTrace(System.err);
        }
    }




}
