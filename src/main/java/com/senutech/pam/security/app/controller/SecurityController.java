package com.senutech.pam.security.app.controller;

import com.senutech.pam.security.app.exception.PamException;
import com.senutech.pam.security.app.model.container.*;
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


            accountCreateRequest.setClientMachine(remoteMachine);
            accountCreateRequest.setRequestRecieptTime(timestamp);
            AccountCreateResult fullResult = securityService.createAccount(accountCreateRequest);
            return new AccountCreateClientResult(fullResult);
        } catch(Exception e) {
            throw PamException.normalize("createAccount REST method failure", e);
        }
    }
    @RequestMapping(value="/s/verifyEmail", method= RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,produces= MediaType.APPLICATION_JSON_VALUE )
    public VerifyEmailResponse verifyEmail(@RequestBody VerifyEmailRequest request) throws PamException {
        try {
            System.out.println("SecurityController.verifyEmail()");
            securityService.validateUserLoginEmail(request.getE(),request.getT());
            return new VerifyEmailResponse(true,"Email verified and account activated");
        } catch(Exception e) {
            throw PamException.normalize("verifyEmail REST method failure", e);
        }

    }

    @RequestMapping(value="/s/allusers", method= RequestMethod.GET,produces= MediaType.APPLICATION_JSON_VALUE )
    public List<Userlogin> getUsers() {
        return userloginRepository.findAll();
    }

}
