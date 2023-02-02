package com.senutech.pam.security.app.repository;

import com.senutech.pam.security.app.SecurityApp;
import com.senutech.pam.security.app.exception.PamException;
import com.senutech.pam.security.app.model.domain.Tranaudit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.OffsetDateTime;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes= SecurityApp.class)
public class TranAuditRepositoryTest {

    @Autowired
    private TranauditRepository tranauditRepository;

    @Test
    public void insertTest() throws Exception {
        try {
            Tranaudit tranaudit = new Tranaudit();
            tranaudit.setId(UUID.randomUUID());
            tranaudit.setUserloginid(UUID.randomUUID());
            tranaudit.setAudittimestamp(OffsetDateTime.now());
            Tranaudit updatedTranaudit  = tranauditRepository.save(tranaudit);

        } catch(Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
