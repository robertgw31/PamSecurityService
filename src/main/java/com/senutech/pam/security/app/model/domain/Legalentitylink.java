package com.senutech.pam.security.app.model.domain;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "legalentitylink")
public class Legalentitylink {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "notes", length = 4000)
    private String notes;

    @Column(name = "isdeleted")
    private Boolean isdeleted;

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

    @Column(name = "legalentityid", nullable = false)
    private UUID legalentityid;

    @Column(name = "owningobjtype", nullable = false, length = 20)
    private String owningobjtype;

    @Column(name = "owningobjid", nullable = false)
    private UUID owningobjid;

    @Column(name = "legalentitytype", nullable = false, length = 100)
    private String legalentitytype;

    @Column(name = "friendlyname", nullable = false, length = 100)
    private String friendlyname;

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

    public Boolean getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(Boolean isdeleted) {
        this.isdeleted = isdeleted;
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

    public UUID getLegalentityid() {
        return legalentityid;
    }

    public void setLegalentityid(UUID legalentityid) {
        this.legalentityid = legalentityid;
    }

    public String getOwningobjtype() {
        return owningobjtype;
    }

    public void setOwningobjtype(String owningobjtype) {
        this.owningobjtype = owningobjtype;
    }

    public UUID getOwningobjid() {
        return owningobjid;
    }

    public void setOwningobjid(UUID owningobjid) {
        this.owningobjid = owningobjid;
    }

    public String getLegalentitytype() {
        return legalentitytype;
    }

    public void setLegalentitytype(String legalentitytype) {
        this.legalentitytype = legalentitytype;
    }

    public String getFriendlyname() {
        return friendlyname;
    }

    public void setFriendlyname(String friendlyname) {
        this.friendlyname = friendlyname;
    }

}