package com.senutech.pam.security.app.model.domain;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "userloginemailhistory")
public class Userloginemailhistory {
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

    @Column(name = "userloginid", nullable = false)
    private UUID userloginid;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "iscurrent", nullable = false)
    private Boolean iscurrent = false;

    @Column(name = "isverified", nullable = false)
    private Boolean isverified = false;

    @Column(name = "verificationcode", length = 20)
    private String verificationcode;

    @Column(name = "replacementtimestamp", nullable = false)
    private OffsetDateTime replacementtimestamp;

    @Column(name = "verificationtimestamp")
    private OffsetDateTime verificationtimestamp;

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

    public UUID getUserloginid() {
        return userloginid;
    }

    public void setUserloginid(UUID userloginid) {
        this.userloginid = userloginid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIscurrent() {
        return iscurrent;
    }

    public void setIscurrent(Boolean iscurrent) {
        this.iscurrent = iscurrent;
    }

    public Boolean getIsverified() {
        return isverified;
    }

    public void setIsverified(Boolean isverified) {
        this.isverified = isverified;
    }

    public String getVerificationcode() {
        return verificationcode;
    }

    public void setVerificationcode(String verificationcode) {
        this.verificationcode = verificationcode;
    }

    public OffsetDateTime getReplacementtimestamp() {
        return replacementtimestamp;
    }

    public void setReplacementtimestamp(OffsetDateTime replacementtimestamp) {
        this.replacementtimestamp = replacementtimestamp;
    }

    public OffsetDateTime getVerificationtimestamp() {
        return verificationtimestamp;
    }

    public void setVerificationtimestamp(OffsetDateTime verificationtimestamp) {
        this.verificationtimestamp = verificationtimestamp;
    }

}