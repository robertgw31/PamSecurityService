package com.senutech.pam.security.app.controller;

import com.senutech.pam.security.app.exception.PamException;
import com.senutech.pam.security.app.model.containers.AccountCreateClientResult;
import com.senutech.pam.security.app.model.containers.AccountCreateRequest;
import com.senutech.pam.security.app.model.containers.AccountCreateResult;
import com.senutech.pam.security.app.model.domain.Account;
import com.senutech.pam.security.app.model.domain.Userlogin;
import com.senutech.pam.security.app.repository.UserloginRepository;
import com.senutech.pam.security.app.service.SecurityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

@RestController
public class SecurityController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserloginRepository userloginRepository;

    @RequestMapping(value="/s/createaccount", method= RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,produces= MediaType.APPLICATION_JSON_VALUE )
    public AccountCreateClientResult createAccount( @RequestBody AccountCreateRequest accountCreateRequest, HttpServletRequest httpRequest) throws PamException {
        try {
            String remoteAddr = httpRequest.getRemoteAddr();
            String remoteHost = httpRequest.getRemoteHost();
            String remoteMachine = "machine.domain.com";
            if (remoteHost != null)
                remoteMachine = remoteHost;
            else if (remoteAddr != null)
                remoteMachine = remoteAddr;

            OffsetDateTime timestamp = OffsetDateTime.now(ZoneOffset.UTC);
            String emailVerificationURLRoot = "http://localhost/s/emailverification/";

            accountCreateRequest.setClientMachine(remoteMachine);
            accountCreateRequest.setRequestRecieptTime(timestamp);
            accountCreateRequest.setEmailVerificationUrlRoot(emailVerificationURLRoot);
            AccountCreateResult fullResult = securityService.createAccount(accountCreateRequest);
            return new AccountCreateClientResult(fullResult);
        } catch(Exception e) {
            throw PamException.normalize("createAccount REST method failure", e);
        }
    }

    public void verifyEmail(HttpServletRequest httpRequest) {

    }

    @RequestMapping(value="/s/allusers", method= RequestMethod.GET,produces= MediaType.APPLICATION_JSON_VALUE )
    public List<Userlogin> getUsers() {
        return userloginRepository.findAll();
    }

}
