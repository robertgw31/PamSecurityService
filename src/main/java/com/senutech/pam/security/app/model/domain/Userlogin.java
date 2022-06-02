package com.senutech.pam.security.app.model.domain;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "userlogin")
public class Userlogin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "notes", length = 4000)
    private String notes;

    @Column(name = "sortorder", nullable = false)
    private Integer sortorder;

    @Column(name = "recversion", nullable = false)
    @Version
    private Long recversion;

    @Column(name = "createtranauditid", nullable = false)
    private UUID createtranauditid;

    @Column(name = "updatetranauditid", nullable = false)
    private UUID updatetranauditid;

    @Column(name = "accountid", nullable = false)
    private UUID accountid;

    @Column(name = "fullname", length = 200)
    private String fullname;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "authprovider", length = 100)
    private String authprovider;

    @Column(name = "authproviderid", length = 200)
    private String authproviderid;

    @Column(name = "imageurl", length = 200)
    private String imageurl;

    @Column(name = "loginpassword", length = 100)
    private String loginpassword;

    @Column(name = "failedloginattempts", nullable = false)
    private Integer failedloginattempts;

    @Column(name = "emailverified", nullable = false)
    private Boolean emailverified = false;

    @Column(name = "lastaccesstimestamp")
    private OffsetDateTime lastaccesstimestamp;

    @Column(name = "status", nullable = false, length = 100)
    private String status;

    @Column(name = "statustimestamp", nullable = false)
    private OffsetDateTime statustimestamp;

    @Column(name = "isclosed", nullable = false)
    private Boolean isclosed = false;

    @Column(name = "isolanguage", length = 20)
    private String isolanguage;

    @Column(name = "isocountrycode", length = 20)
    private String isocountrycode;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getSortorder() {
        return sortorder;
    }

    public void setSortorder(Integer sortorder) {
        this.sortorder = sortorder;
    }

    public Long getRecversion() {
        return recversion;
    }

    public void setRecversion(Long recversion) {
        this.recversion = recversion;
    }

    public UUID getCreatetranauditid() {
        return createtranauditid;
    }

    public void setCreatetranauditid(UUID createtranauditid) {
        this.createtranauditid = createtranauditid;
    }

    public UUID getUpdatetranauditid() {
        return updatetranauditid;
    }

    public void setUpdatetranauditid(UUID updatetranauditid) {
        this.updatetranauditid = updatetranauditid;
    }

    public UUID getAccountid() {
        return accountid;
    }

    public void setAccountid(UUID accountid) {
        this.accountid = accountid;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthprovider() {
        return authprovider;
    }

    public void setAuthprovider(String authprovider) {
        this.authprovider = authprovider;
    }

    public String getAuthproviderid() {
        return authproviderid;
    }

    public void setAuthproviderid(String authproviderid) {
        this.authproviderid = authproviderid;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getLoginpassword() {
        return loginpassword;
    }

    public void setLoginpassword(String loginpassword) {
        this.loginpassword = loginpassword;
    }

    public Integer getFailedloginattempts() {
        return failedloginattempts;
    }

    public void setFailedloginattempts(Integer failedloginattempts) {
        this.failedloginattempts = failedloginattempts;
    }

    public Boolean getEmailverified() {
        return emailverified;
    }

    public void setEmailverified(Boolean emailverified) {
        this.emailverified = emailverified;
    }

    public OffsetDateTime getLastaccesstimestamp() {
        return lastaccesstimestamp;
    }

    public void setLastaccesstimestamp(OffsetDateTime lastaccesstimestamp) {
        this.lastaccesstimestamp = lastaccesstimestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OffsetDateTime getStatustimestamp() {
        return statustimestamp;
    }

    public void setStatustimestamp(OffsetDateTime statustimestamp) {
        this.statustimestamp = statustimestamp;
    }

    public Boolean getIsclosed() {
        return isclosed;
    }

    public void setIsclosed(Boolean isclosed) {
        this.isclosed = isclosed;
    }

    public String getIsolanguage() {
        return isolanguage;
    }

    public void setIsolanguage(String isolanguage) {
        this.isolanguage = isolanguage;
    }

    public String getIsocountrycode() {
        return isocountrycode;
    }

    public void setIsocountrycode(String isocountrycode) {
        this.isocountrycode = isocountrycode;
    }

}