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
@Table(name = "legalentity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Legalentity {
    @Id
    @Column(name = "id", columnDefinition = "uuid", nullable = false, updatable = false)
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

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "companyname", length = 100)
    private String companyname;

    @Column(name = "firstname", length = 100)
    private String firstname;

    @Column(name = "lastname", length = 100)
    private String lastname;

    @Column(name = "displayname", nullable = false, length = 100)
    private String displayname;

    @Column(name = "primaryphone", length = 100)
    private String primaryphone;

    @Column(name = "alternatephone", length = 100)
    private String alternatephone;

    @Column(name = "website", length = 200)
    private String website;

    @Column(name = "address1", length = 100)
    private String address1;

    @Column(name = "address2", length = 100)
    private String address2;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "provence", length = 100)
    private String provence;

    @Column(name = "postalcode", length = 20)
    private String postalcode;

    @Column(name = "country", length = 50)
    private String country;

    @Column(name = "isocountrycode", length = 10)
    private String isocountrycode;

}