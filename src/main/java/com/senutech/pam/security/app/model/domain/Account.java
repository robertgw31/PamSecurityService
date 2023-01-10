package com.senutech.pam.security.app.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Account {

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

    @Column(name = "friendlyname", nullable = false, length = 200)
    private String friendlyname;

    @Column(name = "federationid")
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID federationid;

    @Column(name = "isolanguage", length = 10)
    private String isolanguage;

    @Column(name = "isocountry", length = 20)
    private String isocountry;

    @Column(name = "owneruserloginid", nullable = false)
    @Type(type="org.hibernate.type.PostgresUUIDType")
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

}