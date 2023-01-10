package com.senutech.pam.security.app.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "stockcategory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Stockcategory {
    @Id
    @Column(name = "id", columnDefinition = "uuid", nullable = false, updatable = false)
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID id;

    @Column(name = "sortorder", nullable = false)
    private Integer sortorder;

    @Column(name = "scope", nullable = false, length = 100)
    private String scope;

    @Column(name = "parentid")
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID parentid;

    @Column(name = "code", nullable = false, length = 100)
    private String code;

    @Column(name = "friendlyname", nullable = false, length = 100)
    private String friendlyname;

    @Column(name = "notes", length = 4000)
    private String notes;

    @Column(name = "isolanguage", nullable = false, length = 20)
    private String isolanguage;

}