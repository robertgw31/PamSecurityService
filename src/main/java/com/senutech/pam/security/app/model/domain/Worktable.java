package com.senutech.pam.security.app.model.domain;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "worktable")
public class Worktable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "createtime", nullable = false)
    private OffsetDateTime createtime;

    @Column(name = "tablename", length = 200)
    private String tablename;

    @Column(name = "batchid", nullable = false)
    private UUID batchid;

    @Column(name = "tablerowid", nullable = false)
    private UUID tablerowid;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public OffsetDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(OffsetDateTime createtime) {
        this.createtime = createtime;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public UUID getBatchid() {
        return batchid;
    }

    public void setBatchid(UUID batchid) {
        this.batchid = batchid;
    }

    public UUID getTablerowid() {
        return tablerowid;
    }

    public void setTablerowid(UUID tablerowid) {
        this.tablerowid = tablerowid;
    }

}