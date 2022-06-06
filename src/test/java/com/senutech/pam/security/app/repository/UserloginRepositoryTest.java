package com.senutech.pam.security.app.repository;

import com.senutech.pam.security.app.SecurityApp;
import com.senutech.pam.security.app.exception.PamException;
import com.senutech.pam.security.app.model.domain.Tranaudit;
import com.senutech.pam.security.app.model.domain.Userlogin;
import com.senutech.pam.security.app.util.Constants;
import com.senutech.pam.security.app.util.JsonUtil;
import com.senutech.pam.security.app.util.UserLoginStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes= SecurityApp.class)
public class UserloginRepositoryTest {

    public static final int  MAX_FAILED_LOGIN_ATTEMPTS = Constants.MAX_FAILED_LOGIN_ATTEMPTS;

    @Autowired
    private UserloginRepository userloginRepository;

    @Test
    public void concurrencyTest() {
        try {
            final String email = "robertw@senutech.com";
            Userlogin login = userloginRepository.findByEmailNotClosed(email);
            System.out.println("Object from DB");
            System.out.println(JsonUtil.objectToPrettyJson(login));
            System.out.println("Debug single-step pause to allow async DB update");
            login = userloginRepository.findByEmailNotClosed(email);
            System.out.println(JsonUtil.objectToPrettyJson(login));
            System.out.println("Debug single-step pause to allow async DB update");
            userloginRepository.saveAndFlush(login);
            System.out.println("Debug single-step pause to allow async DB update");
            login = userloginRepository.findByEmailNotClosed(email);
            System.out.println(JsonUtil.objectToPrettyJson(login));
            System.out.println("Debug single-step pause to allow async DB update");
        } catch(Exception e) {
            e.printStackTrace(System.err);
        }
    }


    @Test
    @Transactional
    public void testAllMethods() {
        try {
            Userlogin login = createLogin();
            if (userloginRepository.existsByEmail(login.getEmail())) {
                throw PamException.normalize(String.format("A user with email %s already exists",login.getEmail()),login);
            }
            login = userloginRepository.save(login);
            Assert.notNull(login, "Did not create Userlogin");
            login = userloginRepository.getById(login.getId());
            Assert.notNull(login, "Could not retrieve newly-created login");
            login = userloginRepository.findByEmailNotClosed(login.getEmail());
            Assert.notNull(login, "Could not retrieve login by email");
            Assert.isTrue(userloginRepository.existsByEmailAndStatusTimestamp(login.getEmail(), login.getStatustimestamp()), "Did not validate by email and status timestamp");
            Assert.isTrue(!userloginRepository.reachedMaxFailedLogonAttempts(login.getEmail(),MAX_FAILED_LOGIN_ATTEMPTS), "Unexpected true that userLogin exceeded maximum login attempts");
            login.setFailedloginattempts(MAX_FAILED_LOGIN_ATTEMPTS + 1);
            Tranaudit updateTranaudit = createTranauditForUserLogin(login.getId());
            UUID previousUpdateTranauditId = login.getUpdatetranauditid();
            login.setUpdatetranauditid(updateTranaudit.getId());
            int count = userloginRepository.saveWithConcurrencyCheck(login, login.getId(), previousUpdateTranauditId);
            //Assert.isTrue(count > 0, "Failed to save login");
            Assert.isTrue(userloginRepository.reachedMaxFailedLogonAttempts(login.getEmail(),MAX_FAILED_LOGIN_ATTEMPTS), "Unexpected false that userLogin excceeded maximum login attempts");
            Userlogin login2 = userloginRepository.getById(login.getId());
            System.out.println("Stop");

        } catch(Exception e) {
            e.printStackTrace(System.err);
        }
    }

    private Userlogin createLogin() {

        UUID id = UUID.randomUUID();
        OffsetDateTime timestamp = OffsetDateTime.now(ZoneOffset.UTC);

        Tranaudit tranaudit = createTranauditForUserLogin(id);

        Userlogin login = new Userlogin();
        login.setId(id);
        login.setRecversion(1L);
        login.setCreatetranauditid(tranaudit.getId());
        login.setUpdatetranauditid(tranaudit.getId());
        login.setAccountid(id);
        login.setAuthprovider(null);
        login.setAuthproviderid(null);
        login.setCreatetranauditid(id);
        login.setUpdatetranauditid(id);
        login.setAccountid(id);
        login.setFullname("Robert Wittnebert");
        login.setEmail("robertw@senutech.com");
        login.setImageurl(null);
        login.setLastaccesstimestamp(timestamp);
        login.setFailedloginattempts(0);
        login.setNotes("Account owner");
        login.setStatus(UserLoginStatus.PENDING_EMAIL_VERIFICATION.toString());
        login.setStatustimestamp(timestamp);
        login.setSortorder(1);



        return login;
    }

    private Tranaudit createTranauditForUserLogin(UUID userId) {

        UUID id = UUID.randomUUID();
        OffsetDateTime timestamp = OffsetDateTime.now(ZoneOffset.UTC);

        Tranaudit tranaudit = new Tranaudit();
        tranaudit.setId(id);
        tranaudit.setAudittimestamp(timestamp);
        tranaudit.setUserloginid(userId);
        return tranaudit;
    }
}
