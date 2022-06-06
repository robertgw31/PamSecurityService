package com.senutech.pam.security.app.controller;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.senutech.pam.security.app.SecurityApp;
import com.senutech.pam.security.app.controller.response.ApiErrorResponse;
import com.senutech.pam.security.app.model.containers.AccountCreateClientResult;
import com.senutech.pam.security.app.model.containers.AccountCreateRequest;
import com.senutech.pam.security.app.model.containers.AccountCreateResult;
import com.senutech.pam.security.app.model.domain.*;
import com.senutech.pam.security.app.util.AccountStatus;
import com.senutech.pam.security.app.util.JsonUtil;
import com.senutech.pam.security.app.util.RequestType;
import com.senutech.pam.security.app.util.UserLoginStatus;
import org.apache.catalina.core.ApplicationContext;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;

//@TestPropertySource(locations = "classpath:application-test.properties")

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes= SecurityApp.class)
@AutoConfigureMockMvc
public class SecurityControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(SecurityControllerTest.class);

    @Autowired
    protected MockMvc mvc;

    @Autowired
    protected ObjectMapper objectMapper;


    @Test
    public void testGetAllUsers() {

        try {
            String uri = "/s/allusers";
            MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                    .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

            int status = mvcResult.getResponse().getStatus();
            assertEquals(200, status);
            String content = mvcResult.getResponse().getContentAsString();
            Userlogin[] loginList = objectMapper.readValue(content, Userlogin[].class);
            assertTrue(loginList.length > 0);

        } catch(Exception e) {
            logger.error("",e);
            e.printStackTrace(System.err);
        }
    }

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

            //request.setAccountName("Robert Wittnebert");
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

            String jsonRequest = objectMapper.writeValueAsString(request);
            MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(requestPath)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(jsonRequest)
                    .accept(MediaType.APPLICATION_JSON)).andReturn();

            int status = mvcResult.getResponse().getStatus();
            String content = mvcResult.getResponse().getContentAsString();
            if (status == 200) {
                AccountCreateClientResult result = objectMapper.readValue(content, AccountCreateClientResult.class);
                assertNotNull(result);
                String prettyJson = JsonUtil.objectToPrettyJson(result);
                logger.debug(prettyJson);
            } else {
                ApiErrorResponse result = objectMapper.readValue(content, ApiErrorResponse.class);
                assertNotNull(result);
                String prettyJson = JsonUtil.objectToPrettyJson(result);
                logger.debug(prettyJson);
                System.err.println(prettyJson);
                if (result.getSubErrors() != null && result.getSubErrors().size() >0) {
                    logger.debug("Sub errors exist");
                    System.err.println("Sub errors exist:");
                    result.getSubErrors().forEach(s -> {
                        String se = String.format("\tObject '%s' field '%s' %s for value '%s'",s.getObjectContext(),s.getFieldName(), s.getMessage(),s.getRejectedValue());
                        logger.debug(se);
                        System.err.println(se);
                    });
                }

            }

        } catch(Exception e) {
            logger.error("",e);
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
