package com.senutech.pam.security.app.model.domain;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "notes", length = 4000)
    private String notes;

    @Column(name = "sortorder")
    private Integer sortorder;

    @Column(name = "recversion", nullable = false)
    @Version
    private Long recversion;

    @Column(name = "createtranauditid", nullable = false)
    private UUID createtranauditid;

    @Column(name = "updatetranauditid", nullable = false)
    private UUID updatetranauditid;

    @Column(name = "friendlyname", nullable = false, length = 200)
    private String friendlyname;

    @Column(name = "federationid")
    private UUID federationid;

    @Column(name = "isolanguage", length = 10)
    private String isolanguage;

    @Column(name = "isocountry", length = 20)
    private String isocountry;

    @Column(name = "owneruserloginid", nullable = false)
    private UUID owneruserloginid;

    @Column(name = "status", nullable = false, length = 100)
    private String status;

    @Column(name = "statustimestamp", nullable = false)
    private OffsetDateTime statustimestamp;

    @Column(name = "inmaintenance", nullable = false)
    private Boolean inmaintenance = false;

    @Column(name = "opendate", nullable = false)
    private OffsetDateTime opendate;

    @Column(name = "isclosed", nullable = false)
    private Boolean isclosed = false;

    @Column(name = "closedate")
    private OffsetDateTime closedate;

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

    public String getFriendlyname() {
        return friendlyname;
    }

    public void setFriendlyname(String friendlyname) {
        this.friendlyname = friendlyname;
    }

    public UUID getFederationid() {
        return federationid;
    }

    public void setFederationid(UUID federationid) {
        this.federationid = federationid;
    }

    public String getIsolanguage() {
        return isolanguage;
    }

    public void setIsolanguage(String isolanguage) {
        this.isolanguage = isolanguage;
    }

    public String getIsocountry() {
        return isocountry;
    }

    public void setIsocountry(String isocountry) {
        this.isocountry = isocountry;
    }

    public UUID getOwneruserloginid() {
        return owneruserloginid;
    }

    public void setOwneruserloginid(UUID owneruserloginid) {
        this.owneruserloginid = owneruserloginid;
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

    public Boolean getInmaintenance() {
        return inmaintenance;
    }

    public void setInmaintenance(Boolean inmaintenance) {
        this.inmaintenance = inmaintenance;
    }

    public OffsetDateTime getOpendate() {
        return opendate;
    }

    public void setOpendate(OffsetDateTime opendate) {
        this.opendate = opendate;
    }

    public Boolean getIsclosed() {
        return isclosed;
    }

    public void setIsclosed(Boolean isclosed) {
        this.isclosed = isclosed;
    }

    public OffsetDateTime getClosedate() {
        return closedate;
    }

    public void setClosedate(OffsetDateTime closedate) {
        this.closedate = closedate;
    }

}