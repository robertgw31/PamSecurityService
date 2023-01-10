package com.senutech.pam.security.app.repository;

import com.senutech.pam.security.app.model.domain.Userlogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.UUID;

@Repository
public interface  UserloginRepository extends JpaRepository<Userlogin, UUID> {

    //

    @Modifying
    @Query(value = "update Userlogin l set l = :login where l.id = :logonId and l.updatetranauditid = :existingTranId")
    int saveWithConcurrencyCheck(@Param("login") Userlogin userLogin, @Param("logonId") UUID logonID, @Param("existingTranId") UUID prevUpdateTranauditId);

    @Query("SELECT CASE WHEN COUNT(p.id) > 0 THEN true ELSE false END FROM Userlogin p WHERE p.email = :email and p.isclosed = false")
    boolean existsByEmail(@Param("email") String email);

    @Query("SELECT (CASE WHEN COUNT(p.id) > 0 THEN true ELSE false END) FROM Userlogin p WHERE p.email = :email and p.statustimestamp = :statusTimestamp and p.isclosed = false")
    boolean existsByEmailAndStatusTimestamp(@Param("email") String email, @Param("statusTimestamp") OffsetDateTime statusTimestamp);

    @Query("select p from Userlogin p where p.email = :LoginEmail and p.isclosed = false")
    Userlogin findByEmailNotClosed(@Param("LoginEmail") String email );

    @Query("SELECT CASE WHEN p.failedloginattempts > :maxLogonAttempts then true else false end from Userlogin p where p.email = :loginEmail and p.isclosed = false")
    boolean reachedMaxFailedLogonAttempts(@Param("loginEmail") String logonEmail, @Param("maxLogonAttempts") int maxLogonAttemmpts);

    @Modifying
    @Query("update Userlogin p set p.status = :status, p.statustimestamp = :statusTimestamp, p.updatetranauditid = :updateTranId where p.id = :userLoginId")
    void validateLoginEmail(@Param("userLoginId") UUID userLoginId, @Param("status") String status, @Param("statusTimestamp") OffsetDateTime statusTimestamp, @Param("updateTranId") UUID transAuditID);


}
