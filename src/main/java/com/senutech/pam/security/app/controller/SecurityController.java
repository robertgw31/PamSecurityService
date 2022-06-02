package com.senutech.pam.security.app.controller;

import com.senutech.pam.security.app.exception.PamException;
import com.senutech.pam.security.app.model.containers.AccountCreateRequest;
import com.senutech.pam.security.app.model.containers.AccountCreateResult;
import com.senutech.pam.security.app.model.domain.Account;
import com.senutech.pam.security.app.service.SecurityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@RestController
public class SecurityController {

    @Autowired
    SecurityService securityService;

    public AccountCreateResult createAccount(AccountCreateRequest accountCreateRequest, HttpServletRequest httpRequest) {
        try {
            String clientMachine = "machine.domain.com";
            OffsetDateTime timestamp = OffsetDateTime.now(ZoneOffset.UTC);
            String emailVerificationURLRoot = "http://localhost/s/emailverification/";

            accountCreateRequest.setClientMachine(clientMachine);
            accountCreateRequest.setRequestRecieptTime(timestamp);
            accountCreateRequest.setEmailVerificationUrlRoot(emailVerificationURLRoot);
            return securityService.createAccount(accountCreateRequest);
        } catch(PamException e) {
            e.printStackTrace(System.err);
        }
        return null;
    }

    public void verifyEmail(HttpServletRequest httpRequest) {

    }

}
