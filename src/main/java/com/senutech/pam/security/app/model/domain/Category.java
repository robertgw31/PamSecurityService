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
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Category {
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

    @Column(name = "scope", nullable = false, length = 100)
    private String scope;

    @Column(name = "parentid")
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID parentid;

    @Column(name = "stockcategoryid")
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID stockcategoryid;

    @Column(name = "friendlyname", nullable = false, length = 300)
    private String friendlyname;

}