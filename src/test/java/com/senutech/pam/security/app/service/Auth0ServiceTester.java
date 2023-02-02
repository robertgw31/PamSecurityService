package com.senutech.pam.security.app.service;

import com.senutech.pam.security.app.SecurityApp;
import com.senutech.pam.security.app.exception.PamException;
import com.senutech.pam.security.app.model.auth0.Auth0AccessPass;
import com.senutech.pam.security.app.model.auth0.Auth0User;
import com.senutech.pam.security.app.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes= SecurityApp.class)
public class Auth0ServiceTester {

    @Autowired
    private Auth0Service auth0Service;

//    @Test
//    public void testAquireToken() throws PamException {
//        try {
//            String token = auth0Service.getAccessToken();
//            System.out.printf("Token recieved: %s\n",token);
//
//        }catch(Exception e) {
//           throw PamException.normalize("testAquireToken failed",e);
//        }
//    }

    @Test
    public void testGetUser() throws PamException {
        try {
            String knownEmail = "robertw@senutech.com";
            String unknownEmail = "max@senutech.com";

            System.out.println(String.format("Going after known user with email %s",knownEmail));
            Auth0User knownUser = auth0Service.getUserByEmail(knownEmail);
            String strKnownUser = JsonUtil.objectToPrettyJson(knownUser);
            System.out.println(strKnownUser);

            System.out.println(String.format("Going after unknown user with email %s",unknownEmail));
            Auth0User unknownUser = auth0Service.getUserByEmail(unknownEmail);
            String strUnknownUser = JsonUtil.objectToPrettyJson(knownUser);
            System.out.println(strUnknownUser);
            }catch(Exception e) {
                throw PamException.normalize("testAquireToken failed",e);
            }
    }

    @Test
    public void testResendVerificationEmail() throws PamException {
        try {
            String userEmail = "robertw@senutech.com";
            Auth0User user = auth0Service.getUserByEmail(userEmail);
            if (user == null) {
                throw new PamException(String.format("Was not able to retrieve user information for email%s",userEmail));
            }
            boolean result = auth0Service.resendVerificationEmail(user.getUserId());
            System.out.printf("Request to resend email verification to user with email '%s' was %s",
                    user.getEmail(),
                    (result ? "successful" : "not successful"));

        }catch(Exception e) {
            throw PamException.normalize("testAquireToken failed",e);
        }
    }
}
