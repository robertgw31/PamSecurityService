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
@Table(name = "worktable")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Worktable {
    @Id
    @Column(name = "id", columnDefinition = "uuid", nullable = false, updatable = false)
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID id;

    @Column(name = "createtime", nullable = false)
    private OffsetDateTime createtime;

    @Column(name = "tablename", length = 200)
    private String tablename;

    @Column(name = "batchid", nullable = false)
    private UUID batchid;

    @Column(name = "tablerowid", nullable = false)
    private UUID tablerowid;

}