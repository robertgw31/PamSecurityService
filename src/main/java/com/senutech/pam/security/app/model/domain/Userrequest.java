package com.senutech.pam.security.app.model.domain;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "userrequest")
public class Userrequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "accountid", nullable = false)
    private UUID accountid;

    @Column(name = "usersessionid", nullable = false)
    private UUID usersessionid;

    @Column(name = "userloginid", nullable = false)
    private UUID userloginid;

    @Column(name = "requesttype", nullable = false, length = 100)
    private String requesttype;

    @Column(name = "requestdetails", length = 500)
    private String requestdetails;

    @Column(name = "starttime", nullable = false)
    private OffsetDateTime starttime;

    @Column(name = "endtime", nullable = false)
    private OffsetDateTime endtime;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getAccountid() {
        return accountid;
    }

    public void setAccountid(UUID accountid) {
        this.accountid = accountid;
    }

    public UUID getUsersessionid() {
        return usersessionid;
    }

    public void setUsersessionid(UUID usersessionid) {
        this.usersessionid = usersessionid;
    }

    public UUID getUserloginid() {
        return userloginid;
    }

    public void setUserloginid(UUID userloginid) {
        this.userloginid = userloginid;
    }

    public String getRequesttype() {
        return requesttype;
    }

    public void setRequesttype(String requesttype) {
        this.requesttype = requesttype;
    }

    public String getRequestdetails() {
        return requestdetails;
    }

    public void setRequestdetails(String requestdetails) {
        this.requestdetails = requestdetails;
    }

    public OffsetDateTime getStarttime() {
        return starttime;
    }

    public void setStarttime(OffsetDateTime starttime) {
        this.starttime = starttime;
    }

    public OffsetDateTime getEndtime() {
        return endtime;
    }

    public void setEndtime(OffsetDateTime endtime) {
        this.endtime = endtime;
    }

}