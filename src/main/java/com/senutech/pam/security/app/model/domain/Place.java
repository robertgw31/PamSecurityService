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
@Table(name = "place")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Place {
    @Id
    @Column(name = "id", columnDefinition = "uuid", nullable = false, updatable = false)
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID id;

    @Column(name = "stockroomid")
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID stockroomid;

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

    @Column(name = "parentid")
    private UUID parentid;

    @Column(name = "friendlyname", nullable = false, length = 300)
    private String friendlyname;

    @Column(name = "placetype", nullable = false, length = 20)
    private String placetype;

    @Column(name = "address1", length = 100)
    private String address1;

    @Column(name = "address2", length = 100)
    private String address2;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "provence", length = 100)
    private String provence;

    @Column(name = "country", length = 100)
    private String country;

    @Column(name = "postalcode", length = 100)
    private String postalcode;

}