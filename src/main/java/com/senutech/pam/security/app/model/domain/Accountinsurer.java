package com.senutech.pam.security.app.model.domain;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "accountinsurer")
public class Accountinsurer {
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

    @Column(name = "accountid", nullable = false)
    private UUID accountid;

    @Column(name = "carriername", nullable = false, length = 100)
    private String carriername;

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

    public String getCarriername() {
        return carriername;
    }

    public void setCarriername(String carriername) {
        this.carriername = carriername;
    }

}