package com.senutech.pam.security.app.model.domain;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "usersession")
public class Usersession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "accountid", nullable = false)
    private UUID accountid;

    @Column(name = "userloginid", nullable = false)
    private UUID userloginid;

    @Column(name = "clientmachine", length = 200)
    private String clientmachine;

    @Column(name = "notes", length = 4000)
    private String notes;

    @Column(name = "starttime", nullable = false)
    private OffsetDateTime starttime;

    @Column(name = "endtime")
    private OffsetDateTime endtime;

    @Column(name = "isactive", nullable = false)
    private Boolean isactive = false;

    @Column(name = "wastimedout", nullable = false)
    private Boolean wastimedout = false;

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

    public UUID getUserloginid() {
        return userloginid;
    }

    public void setUserloginid(UUID userloginid) {
        this.userloginid = userloginid;
    }

    public String getClientmachine() {
        return clientmachine;
    }

    public void setClientmachine(String clientmachine) {
        this.clientmachine = clientmachine;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

    public Boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

    public Boolean getWastimedout() {
        return wastimedout;
    }

    public void setWastimedout(Boolean wastimedout) {
        this.wastimedout = wastimedout;
    }

}