package com.senutech.pam.security.app.model.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "purchase")
public class Purchase {
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

    @Column(name = "datasource", nullable = false, length = 200)
    private String datasource;

    @Column(name = "transactiondate")
    private OffsetDateTime transactiondate;

    @Column(name = "paymenttype", nullable = false, length = 200)
    private String paymenttype;

    @Column(name = "creditcardholder", length = 300)
    private String creditcardholder;

    @Column(name = "creditcardtype", length = 300)
    private String creditcardtype;

    @Column(name = "creditcardnumber", length = 100)
    private String creditcardnumber;

    @Column(name = "creditcardexpirationmoyear", length = 4)
    private String creditcardexpirationmoyear;

    @Column(name = "creditcardcsv", length = 10)
    private String creditcardcsv;

    @Column(name = "purchaseamount", nullable = false, precision = 12, scale = 2)
    private BigDecimal purchaseamount;

    @Column(name = "currencycode", nullable = false, length = 3)
    private String currencycode;

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

    public String getDatasource() {
        return datasource;
    }

    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }

    public OffsetDateTime getTransactiondate() {
        return transactiondate;
    }

    public void setTransactiondate(OffsetDateTime transactiondate) {
        this.transactiondate = transactiondate;
    }

    public String getPaymenttype() {
        return paymenttype;
    }

    public void setPaymenttype(String paymenttype) {
        this.paymenttype = paymenttype;
    }

    public String getCreditcardholder() {
        return creditcardholder;
    }

    public void setCreditcardholder(String creditcardholder) {
        this.creditcardholder = creditcardholder;
    }

    public String getCreditcardtype() {
        return creditcardtype;
    }

    public void setCreditcardtype(String creditcardtype) {
        this.creditcardtype = creditcardtype;
    }

    public String getCreditcardnumber() {
        return creditcardnumber;
    }

    public void setCreditcardnumber(String creditcardnumber) {
        this.creditcardnumber = creditcardnumber;
    }

    public String getCreditcardexpirationmoyear() {
        return creditcardexpirationmoyear;
    }

    public void setCreditcardexpirationmoyear(String creditcardexpirationmoyear) {
        this.creditcardexpirationmoyear = creditcardexpirationmoyear;
    }

    public String getCreditcardcsv() {
        return creditcardcsv;
    }

    public void setCreditcardcsv(String creditcardcsv) {
        this.creditcardcsv = creditcardcsv;
    }

    public BigDecimal getPurchaseamount() {
        return purchaseamount;
    }

    public void setPurchaseamount(BigDecimal purchaseamount) {
        this.purchaseamount = purchaseamount;
    }

    public String getCurrencycode() {
        return currencycode;
    }

    public void setCurrencycode(String currencycode) {
        this.currencycode = currencycode;
    }

}