package com.senutech.pam.app;

import com.senutech.pam.security.app.SecurityApp;
import com.senutech.pam.security.app.model.domain.Tranaudit;
import com.senutech.pam.security.app.model.domain.Userlogin;
import com.senutech.pam.security.app.model.domain.Userrequest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes= SecurityApp.class)
public class TesterBase {

    public Tranaudit createTranauditForUserLogin(UUID userId) {

        UUID id = UUID.randomUUID();
        OffsetDateTime timestamp = OffsetDateTime.now(ZoneOffset.UTC);

        Tranaudit tranaudit = new Tranaudit();
        tranaudit.setId(id);
        tranaudit.setAudittimestamp(timestamp);
        tranaudit.setUserloginid(userId);
        return tranaudit;
    }

    public Tranaudit createTranauditForRequestId(UUID requestId) {

        UUID id = UUID.randomUUID();
        OffsetDateTime timestamp = OffsetDateTime.now(ZoneOffset.UTC);

        Tranaudit tranaudit = new Tranaudit();
        tranaudit.setId(id);
        tranaudit.setAudittimestamp(timestamp);
        tranaudit.setLoginrequestid(requestId);
        return tranaudit;
    }

    public Tranaudit createTranauditForUserName(String userName) {

        UUID id = UUID.randomUUID();
        OffsetDateTime timestamp = OffsetDateTime.now(ZoneOffset.UTC);

        Tranaudit tranaudit = new Tranaudit();
        tranaudit.setId(id);
        tranaudit.setAudittimestamp(timestamp);
        tranaudit.setUsername(userName);
        return tranaudit;
    }
}
