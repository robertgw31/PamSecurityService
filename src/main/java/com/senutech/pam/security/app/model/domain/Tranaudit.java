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
@Table(name = "tranaudit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Tranaudit {
    @Id
    @Column(name = "id", columnDefinition = "uuid", nullable = false, updatable = false)
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID id;

    @Column(name = "loginrequestid")
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID loginrequestid;

    @Column(name = "userloginid")
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID userloginid;

    @Column(name = "username", length = 100)
    private String username;

    @Column(name = "audittimestamp", nullable = false)
    private OffsetDateTime audittimestamp;

}