package com.senutech.pam.security.app.repository;

import com.senutech.pam.security.app.model.domain.Usersession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.UUID;

@Repository
public interface UsersessionRepository extends JpaRepository<Usersession, UUID> {

    @Transactional
    @Modifying
    @Query("update Usersession Set isactive = false, endtime = :logoutTime where id = :id")
    void logout(UUID id, OffsetDateTime logoutTime);

    @Transactional
    @Modifying
    @Query("update Usersession set isactive = false, wastimedout = true, endtime = :logoutTime where id = :id")
    void timeout(UUID id, OffsetDateTime logoutTime);
}
