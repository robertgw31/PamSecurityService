package com.senutech.pam.security.app.model.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "asset")
public class Asset {
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

    @Column(name = "friendlyname", nullable = false, length = 300)
    private String friendlyname;

    @Column(name = "placeid")
    private UUID placeid;

    @Column(name = "placename", length = 300)
    private String placename;

    @Column(name = "categoryid")
    private UUID categoryid;

    @Column(name = "categoryname", length = 300)
    private String categoryname;

    @Column(name = "ownername", length = 100)
    private String ownername;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "purchaseprice", precision = 12, scale = 5)
    private BigDecimal purchaseprice;

    @Column(name = "replacementvalue", precision = 12, scale = 5)
    private BigDecimal replacementvalue;

    @Column(name = "currencycode", length = 3)
    private String currencycode;

    @Column(name = "purchasedate")
    private OffsetDateTime purchasedate;

    @Column(name = "purchasedat", length = 300)
    private String purchasedat;

    @Column(name = "make", length = 300)
    private String make;

    @Column(name = "modelname", length = 300)
    private String modelname;

    @Column(name = "modelnum", length = 100)
    private String modelnum;

    @Column(name = "serialnum", length = 100)
    private String serialnum;

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

    public String getFriendlyname() {
        return friendlyname;
    }

    public void setFriendlyname(String friendlyname) {
        this.friendlyname = friendlyname;
    }

    public UUID getPlaceid() {
        return placeid;
    }

    public void setPlaceid(UUID placeid) {
        this.placeid = placeid;
    }

    public String getPlacename() {
        return placename;
    }

    public void setPlacename(String placename) {
        this.placename = placename;
    }

    public UUID getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(UUID categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPurchaseprice() {
        return purchaseprice;
    }

    public void setPurchaseprice(BigDecimal purchaseprice) {
        this.purchaseprice = purchaseprice;
    }

    public BigDecimal getReplacementvalue() {
        return replacementvalue;
    }

    public void setReplacementvalue(BigDecimal replacementvalue) {
        this.replacementvalue = replacementvalue;
    }

    public String getCurrencycode() {
        return currencycode;
    }

    public void setCurrencycode(String currencycode) {
        this.currencycode = currencycode;
    }

    public OffsetDateTime getPurchasedate() {
        return purchasedate;
    }

    public void setPurchasedate(OffsetDateTime purchasedate) {
        this.purchasedate = purchasedate;
    }

    public String getPurchasedat() {
        return purchasedat;
    }

    public void setPurchasedat(String purchasedat) {
        this.purchasedat = purchasedat;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModelname() {
        return modelname;
    }

    public void setModelname(String modelname) {
        this.modelname = modelname;
    }

    public String getModelnum() {
        return modelnum;
    }

    public void setModelnum(String modelnum) {
        this.modelnum = modelnum;
    }

    public String getSerialnum() {
        return serialnum;
    }

    public void setSerialnum(String serialnum) {
        this.serialnum = serialnum;
    }

}