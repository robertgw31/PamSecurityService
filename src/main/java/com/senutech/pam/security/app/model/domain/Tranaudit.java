package com.senutech.pam.security.app.model.domain;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "tranaudit")
public class Tranaudit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "loginrequestid")
    private UUID loginrequestid;

    @Column(name = "userloginid")
    private UUID userloginid;

    @Column(name = "username", length = 100)
    private String username;

    @Column(name = "audittimestamp", nullable = false)
    private OffsetDateTime audittimestamp;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getLoginrequestid() {
        return loginrequestid;
    }

    public void setLoginrequestid(UUID loginrequestid) {
        this.loginrequestid = loginrequestid;
    }

    public UUID getUserloginid() {
        return userloginid;
    }

    public void setUserloginid(UUID userloginid) {
        this.userloginid = userloginid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public OffsetDateTime getAudittimestamp() {
        return audittimestamp;
    }

    public void setAudittimestamp(OffsetDateTime audittimestamp) {
        this.audittimestamp = audittimestamp;
    }

}