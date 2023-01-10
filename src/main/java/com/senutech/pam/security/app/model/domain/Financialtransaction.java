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
@Table(name = "financialtransaction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Financialtransaction {
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

    @Column(name = "transactiontype", nullable = false, length = 10)
    private String transactiontype;

    @Column(name = "debitcredit", nullable = false, length = 10)
    private String debitcredit;

    @Column(name = "transactiontime")
    private OffsetDateTime transactiontime;

    @Column(name = "amount", nullable = false, precision = 12, scale = 5)
    private BigDecimal amount;

    @Column(name = "currencycode", nullable = false, length = 3)
    private String currencycode;

}