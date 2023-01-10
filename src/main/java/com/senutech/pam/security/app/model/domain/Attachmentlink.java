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
@Table(name = "attachmentlink")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Attachmentlink {
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

    @Column(name = "attachmentid", nullable = false)
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID attachmentid;

    @Column(name = "owningobjtype", nullable = false, length = 20)
    private String owningobjtype;

    @Column(name = "owningobjid", nullable = false)
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID owningobjid;

    @Column(name = "attachmenttype", length = 100)
    private String attachmenttype;

    @Column(name = "friendlyname", length = 100)
    private String friendlyname;

    @Column(name = "isdeleted", nullable = false)
    private Boolean isdeleted = false;

}