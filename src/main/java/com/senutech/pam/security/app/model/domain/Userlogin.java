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
@Table(name = "userlogin")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Userlogin {
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

    @Column(name = "fullname", length = 200)
    private String fullname;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "authprovider", length = 100)
    private String authprovider;

    @Column(name = "authproviderid", length = 200)
    private String authproviderid;

    @Column(name = "imageurl", length = 200)
    private String imageurl;

    @Column(name = "loginpassword", length = 100)
    private String loginpassword;

    @Column(name = "failedloginattempts", nullable = false)
    private Integer failedloginattempts;

    @Column(name = "emailverificationtoken", length = 200)
    private String emailVerificationToken;

    @Column(name = "emailverified", nullable = false)
    private Boolean emailverified = false;

    @Column(name = "lastaccesstimestamp")
    private OffsetDateTime lastaccesstimestamp;

    @Column(name = "status", nullable = false, length = 100)
    private String status;

    @Column(name = "statustimestamp", nullable = false)
    private OffsetDateTime statustimestamp;

    @Column(name = "isclosed", nullable = false)
    private Boolean isclosed = false;

    @Column(name = "isolanguage", length = 20)
    private String isolanguage;

    @Column(name = "isocountrycode", length = 20)
    private String isocountrycode;

}