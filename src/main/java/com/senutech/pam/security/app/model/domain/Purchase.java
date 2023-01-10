package com.senutech.pam.security.app.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "purchase")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Purchase {
    @Id
    @Column(name = "id", columnDefinition = "uuid", nullable = false, updatable = false)
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID id;

    @Column(name = "notes", length = 4000)
    private String notes;

    @Column(name = "sortorder", nullable = false)
    private Integer sortorder;

    @Column(name = "recversion", nullable = false)
    @Version
    private Long recversion;

    @Column(name = "createtranauditid", nullable = false)
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID createtranauditid;

    @Column(name = "updatetranauditid", nullable = false)
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID updatetranauditid;

    @Column(name = "accountid", nullable = false)
    @Type(type="org.hibernate.type.PostgresUUIDType")
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

}