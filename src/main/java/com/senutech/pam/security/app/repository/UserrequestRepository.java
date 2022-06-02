package com.senutech.pam.security.app.repository;

import com.senutech.pam.security.app.model.domain.Userrequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.UUID;

@Repository
public interface UserrequestRepository extends JpaRepository<Userrequest, UUID> {

    @Transactional
    @Modifying
    @Query("update Userrequest set endtime = ?2 where id = ?1")
    void markRequestCompleteTime(UUID requestId, OffsetDateTime endTime);
}
