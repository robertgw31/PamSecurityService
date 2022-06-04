package com.senutech.pam.security.app.controller;

import com.senutech.pam.security.app.exception.PamException;
import com.senutech.pam.security.app.model.containers.AccountCreateClientResult;
import com.senutech.pam.security.app.model.containers.AccountCreateRequest;
import com.senutech.pam.security.app.model.containers.AccountCreateResult;
import com.senutech.pam.security.app.model.domain.Account;
import com.senutech.pam.security.app.service.SecurityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@RestController
public class SecurityController {

    @Autowired
    SecurityService securityService;

    @RequestMapping(value="/s/createaccount", method= RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,produces= MediaType.APPLICATION_JSON_VALUE )
    public AccountCreateClientResult createAccount(AccountCreateRequest accountCreateRequest, HttpServletRequest httpRequest) {
        try {
            String clientMachine = "machine.domain.com";
            OffsetDateTime timestamp = OffsetDateTime.now(ZoneOffset.UTC);
            String emailVerificationURLRoot = "http://localhost/s/emailverification/";

            accountCreateRequest.setClientMachine(clientMachine);
            accountCreateRequest.setRequestRecieptTime(timestamp);
            accountCreateRequest.setEmailVerificationUrlRoot(emailVerificationURLRoot);
            AccountCreateResult fullResult = securityService.createAccount(accountCreateRequest);
            return new AccountCreateClientResult(fullResult);
        } catch(PamException e) {
            e.printStackTrace(System.err);
        }
        return null;
    }

    public void verifyEmail(HttpServletRequest httpRequest) {

    }

}
