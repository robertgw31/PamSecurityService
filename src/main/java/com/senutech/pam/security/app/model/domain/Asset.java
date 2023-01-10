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
@Table(name = "asset")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Asset {
    @Id
    @Column(name = "id", columnDefinition = "uuid", nullable = false, updatable = false)
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID id;

    @Column(name = "notes", length = 4000)
    private String notes;

    @Column(name = "sortorder")
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

    @Column(name = "friendlyname", nullable = false, length = 300)
    private String friendlyname;

    @Column(name = "placeid")
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID placeid;

    @Column(name = "placename", length = 300)
    private String placename;

    @Column(name = "categoryid")
    @Type(type="org.hibernate.type.PostgresUUIDType")
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

}