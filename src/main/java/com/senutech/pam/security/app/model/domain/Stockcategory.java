package com.senutech.pam.security.app.model.domain;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "stockcategory")
public class Stockcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "sortorder", nullable = false)
    private Integer sortorder;

    @Column(name = "scope", nullable = false, length = 100)
    private String scope;

    @Column(name = "parentid")
    private UUID parentid;

    @Column(name = "code", nullable = false, length = 100)
    private String code;

    @Column(name = "friendlyname", nullable = false, length = 100)
    private String friendlyname;

    @Column(name = "notes", length = 4000)
    private String notes;

    @Column(name = "isolanguage", nullable = false, length = 20)
    private String isolanguage;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getSortorder() {
        return sortorder;
    }

    public void setSortorder(Integer sortorder) {
        this.sortorder = sortorder;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public UUID getParentid() {
        return parentid;
    }

    public void setParentid(UUID parentid) {
        this.parentid = parentid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFriendlyname() {
        return friendlyname;
    }

    public void setFriendlyname(String friendlyname) {
        this.friendlyname = friendlyname;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getIsolanguage() {
        return isolanguage;
    }

    public void setIsolanguage(String isolanguage) {
        this.isolanguage = isolanguage;
    }

}