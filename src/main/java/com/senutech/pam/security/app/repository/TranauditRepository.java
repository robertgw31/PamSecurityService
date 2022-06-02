package com.senutech.pam.security.app.repository;

import com.senutech.pam.security.app.model.domain.Tranaudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TranauditRepository extends JpaRepository<Tranaudit, UUID> {
}
