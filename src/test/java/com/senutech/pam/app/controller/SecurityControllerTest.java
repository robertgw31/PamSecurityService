package com.senutech.pam.app.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.senutech.pam.security.app.SecurityApp;
import com.senutech.pam.security.app.controller.SecurityController;
import com.senutech.pam.security.app.model.containers.AccountCreateClientResult;
import com.senutech.pam.security.app.model.containers.AccountCreateRequest;
import com.senutech.pam.security.app.model.containers.AccountCreateResult;
import com.senutech.pam.security.app.model.domain.*;
import com.senutech.pam.security.app.service.SecurityService;
import com.senutech.pam.security.app.util.AccountStatus;
import com.senutech.pam.security.app.util.RequestType;
import com.senutech.pam.security.app.util.UserLoginStatus;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.postgresql.hostchooser.HostRequirement.any;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@TestPropertySource(locations = "classpath:application-test.properties")

@SpringBootTest(classes= SecurityApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class SecurityControllerTest {

    @InjectMocks
    private SecurityController securityController;

    @Mock
    private SecurityService securityService;

    @Autowired
    private MockMvc mockMvc;



    private static final ObjectMapper mapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();


    @Test
    public void testCreateAccount() {

        String localServer = "http://localhost:8080";
        String requestPath = "/s/createaccount";
        String requestUrl = String.format("%s%s",localServer,requestPath);
        ZoneId zone = ZoneId.of("America/New_York");
        OffsetDateTime timestamp = OffsetDateTime.now(zone);
        String verificationUrl = "http://www.cnn.com";
        try {
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

           // when(securityService.createAccount(request)).thenReturn(createResult(request));
            when(securityService.createAccount(any())).thenReturn(createResult(request));
            RequestBuilder requestBuilder = MockMvcRequestBuilders.post(requestPath)
                    .accept(MediaType.APPLICATION_JSON_VALUE)
                    .content(mapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON_VALUE);

            MvcResult result = mockMvc.perform(requestBuilder).andReturn();
            MockHttpServletResponse response = result.getResponse();
            String strResult = response.getContentAsString();
            AccountCreateClientResult accountCreateResult = mapper.readValue(strResult,AccountCreateClientResult.class);

        } catch(Exception e) {
            e.printStackTrace(System.err);
        }
    }

    private AccountCreateResult createResult(AccountCreateRequest request) {

        AccountCreateResult result = new AccountCreateResult();
        UUID id = UUID.randomUUID();
        OffsetDateTime  timestamp = OffsetDateTime.now(ZoneOffset.UTC);



        request.setOpenDateTime(timestamp); // override provided d
        request.setTimestamp(timestamp);
        request.setId(UUID.randomUUID());

        Tranaudit tranaudit = new Tranaudit();
        tranaudit.setId(id);
        tranaudit.setLoginrequestid(id);
        tranaudit.setUserloginid(id);
        tranaudit.setAudittimestamp(timestamp);

        Userlogin login = new Userlogin();
        login.setId(id);
        login.setAccountid(id);
        login.setAuthprovider(request.getLoginAuthProvider());
        login.setAuthproviderid(request.getLoginAuthProvider());
        login.setCreatetranauditid(id);
        login.setUpdatetranauditid(id);
        login.setAccountid(id);
        login.setFullname(request.getLoginFullName());
        login.setEmail(request.getLoginEmail());
        login.setImageurl(request.getLoginImageURL());
        login.setLastaccesstimestamp(timestamp);
        login.setFailedloginattempts(0);
        login.setNotes("Account owner");
        login.setStatus(UserLoginStatus.PENDING_EMAIL_VERIFICATION.toString());
        login.setStatustimestamp(timestamp);
        login.setRecversion(1L);
        login.setSortorder(1);

        Usersession usersession = new Usersession();
        usersession.setId(id);
        usersession.setUserloginid(id);
        usersession.setStarttime(timestamp);
        usersession.setAccountid(id);
        usersession.setIsactive(true);
        usersession.setWastimedout(false);
        usersession.setNotes("User's first session to create login and account");
        usersession.setStarttime(timestamp);

        usersession.setClientmachine(request.getClientMachine());

        Userrequest userrequest = new Userrequest();
        userrequest.setAccountid(id);
        userrequest.setId(id);
        userrequest.setRequesttype(RequestType.ACCOUNT_CREATION.toString());
        userrequest.setRequestdetails("Creating new account and login");
        userrequest.setUsersessionid(id);
        userrequest.setUserloginid(id);
        userrequest.setStarttime(request.getRequestRecieptTime());
        userrequest.setEndtime(timestamp);

        Account account = new Account();
        account.setId(id);
        account.setFriendlyname(request.getLoginFullName());
        account.setCreatetranauditid(id);
        account.setUpdatetranauditid(id);
        account.setIsocountry(request.getIsoCountry());
        account.setIsolanguage(request.getIsoLanguage());
        account.setOpendate(timestamp);
        account.setOwneruserloginid(id);
        account.setStatus(AccountStatus.PENDING_ACTIVATION.toString());
        account.setStatustimestamp(timestamp);
        account.setIsclosed(false);
        account.setRecversion(1L);
        account.setSortorder(1);

        result.setAccount(account);
        result.setUserlogin(login);
        result.setTranaudit(tranaudit);
        result.setUserrequest(userrequest);
        result.setUsersession(usersession);
        return result;
    }
}
