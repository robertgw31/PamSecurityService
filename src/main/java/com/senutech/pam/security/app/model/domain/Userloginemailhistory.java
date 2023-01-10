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
@Table(name = "userloginemailhistory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Userloginemailhistory {
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

    @Column(name = "userloginid", nullable = false)
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID userloginid;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "iscurrent", nullable = false)
    private Boolean iscurrent = false;

    @Column(name = "isverified", nullable = false)
    private Boolean isverified = false;

    @Column(name = "verificationcode", length = 20)
    private String verificationcode;

    @Column(name = "replacementtimestamp", nullable = false)
    private OffsetDateTime replacementtimestamp;

    @Column(name = "verificationtimestamp")
    private OffsetDateTime verificationtimestamp;

}