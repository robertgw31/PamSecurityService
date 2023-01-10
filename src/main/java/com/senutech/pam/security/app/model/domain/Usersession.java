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
@Table(name = "usersession")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Usersession {
    @Id
    @Column(name = "id", columnDefinition = "uuid", nullable = false, updatable = false)
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID id;

    @Column(name = "accountid", nullable = false)
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID accountid;

    @Column(name = "userloginid", nullable = false)
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID userloginid;

    @Column(name = "clientmachine", length = 200)
    private String clientmachine;

    @Column(name = "notes", length = 4000)
    private String notes;

    @Column(name = "starttime", nullable = false)
    private OffsetDateTime starttime;

    @Column(name = "endtime")
    private OffsetDateTime endtime;

    @Column(name = "isactive", nullable = false)
    private Boolean isactive = false;

    @Column(name = "wastimedout", nullable = false)
    private Boolean wastimedout = false;

}