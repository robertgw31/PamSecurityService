package com.senutech.pam.security.app.repository;

import com.senutech.pam.security.app.model.domain.Account;
import com.senutech.pam.security.app.util.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    public static final String ACTIVE = AccountStatus.ACTIVE.toString();

    @Modifying
    @Query("update Account p set p.status = :ACTIVE, p.statustimestamp = :statusTimestamp, p.updatetranauditid = :updateTranId where p.owneruserloginid = :userLoginId and p.isclosed = false")
    void activateAccountForPrimaryUserActivation(@Param("userLoginId") UUID userLoginId, @Param("statusTimestamp") OffsetDateTime statusTimestamp, @Param("updateTranId") UUID transAuditID);

}
