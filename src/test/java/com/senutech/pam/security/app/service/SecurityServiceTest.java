package com.senutech.pam.security.app.service;

import com.senutech.pam.security.app.SecurityApp;
import com.senutech.pam.security.app.model.container.AccountCreateRequest;
import com.senutech.pam.security.app.model.container.AccountCreateResult;
import com.senutech.pam.security.app.model.container.VerifyEmailRequest;
import com.senutech.pam.security.app.model.container.VerifyEmailResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.OffsetDateTime;
import java.time.ZoneId;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes= SecurityApp.class)
public class SecurityServiceTest {

    @Autowired
    private SecurityService securityService;

    @Test
    public void createAccount() throws Exception {
        try {
            System.out.printf("server port = %s\n",System.getProperty("server.port"));

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
            request.setEmailVerificationUrl("https://pam.com?e=robertw@senutech.com&t=xxxx");
            AccountCreateResult res = securityService.createAccount(request);

        } catch(Exception e) {
            e.printStackTrace(System.err);
            throw e;
        }
    }

    @Test
    public void testValidateUserLoginEmail() throws Exception {
        try {
            VerifyEmailRequest req = new VerifyEmailRequest();
            req.setT("XXXXX");

            boolean success = true;
            String testEmail = "";
            VerifyEmailResponse res = null;

            testEmail = "robertgw31@gmail.com";
            req.setE(testEmail);
            res = securityService.validateUserLoginEmail(req);
            success = (res.isExists() == false);
            System.out.println(String.format("Validation of email for '%s' was %s -- account %s exist and/or %s active",
                    testEmail,
                    success,
                    (res.isExists() ? "DOES" : "DOES NOT"),
                    (res.isExists() ? "IS" : "IS NOT")
                    ));

            testEmail = "robertw@senutech.com";
            req.setE(testEmail);
            res = securityService.validateUserLoginEmail(req);
            success = (res.isExists() == true);
            System.out.println(String.format("Validation of email for '%s' was %s -- account %s exist and/or %s active",
                    testEmail,
                    success,
                    (res.isExists() ? "DOES" : "DOES NOT"),
                    (res.isExists() ? "IS" : "IS NOT")
            ));

        } catch(Exception e) {
            e.printStackTrace(System.err);
            throw e;
        }
    }




}
