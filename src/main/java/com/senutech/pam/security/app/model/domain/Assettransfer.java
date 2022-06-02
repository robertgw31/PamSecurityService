package com.senutech.pam.security.app.model.domain;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "assettransfer")
public class Assettransfer {
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

    @Column(name = "transfertype", nullable = false, length = 20)
    private String transfertype;

    @Column(name = "isacquisition", nullable = false)
    private Boolean isacquisition = false;

    @Column(name = "financialtransactionid")
    private UUID financialtransactionid;

    @Column(name = "assetid", nullable = false)
    private UUID assetid;

    @Column(name = "transfertime")
    private OffsetDateTime transfertime;

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

    public String getTransfertype() {
        return transfertype;
    }

    public void setTransfertype(String transfertype) {
        this.transfertype = transfertype;
    }

    public Boolean getIsacquisition() {
        return isacquisition;
    }

    public void setIsacquisition(Boolean isacquisition) {
        this.isacquisition = isacquisition;
    }

    public UUID getFinancialtransactionid() {
        return financialtransactionid;
    }

    public void setFinancialtransactionid(UUID financialtransactionid) {
        this.financialtransactionid = financialtransactionid;
    }

    public UUID getAssetid() {
        return assetid;
    }

    public void setAssetid(UUID assetid) {
        this.assetid = assetid;
    }

    public OffsetDateTime getTransfertime() {
        return transfertime;
    }

    public void setTransfertime(OffsetDateTime transfertime) {
        this.transfertime = transfertime;
    }

}