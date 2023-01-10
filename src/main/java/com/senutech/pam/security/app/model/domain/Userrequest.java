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
@Table(name = "userrequest")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Userrequest {
    @Id
    @Column(name = "id", columnDefinition = "uuid", nullable = false, updatable = false)
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID id;

    @Column(name = "accountid", nullable = false)
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID accountid;

    @Column(name = "usersessionid", nullable = false)
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID usersessionid;

    @Column(name = "userloginid", nullable = false)
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID userloginid;

    @Column(name = "requesttype", nullable = false, length = 100)
    private String requesttype;

    @Column(name = "requestdetails", length = 500)
    private String requestdetails;

    @Column(name = "starttime", nullable = false)
    private OffsetDateTime starttime;

    @Column(name = "endtime", nullable = false)
    private OffsetDateTime endtime;

}