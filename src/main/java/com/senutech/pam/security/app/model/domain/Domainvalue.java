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
@Table(name = "domainvalue")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Domainvalue {
    @Id
    @Column(name = "id", columnDefinition = "uuid", nullable = false, updatable = false)
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID id;

    @Column(name = "createtime", nullable = false)
    private OffsetDateTime createtime;

    @Column(name = "domain", nullable = false, length = 200)
    private String domain;

    @Column(name = "isolanguage", nullable = false, length = 10)
    private String isolanguage;

    @Column(name = "isolanguagetag", nullable = false, length = 10)
    private String isolanguagetag;

    @Column(name = "k", nullable = false, length = 200)
    private String k;

    @Column(name = "v", nullable = false, length = 500)
    private String v;

    @Column(name = "notes", length = 1000)
    private String notes;

}