package com.senutech.pam.security.app.model.domain;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "domainvalue")
public class Domainvalue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
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

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getIsolanguage() {
        return isolanguage;
    }

    public void setIsolanguage(String isolanguage) {
        this.isolanguage = isolanguage;
    }

    public String getIsolanguagetag() {
        return isolanguagetag;
    }

    public void setIsolanguagetag(String isolanguagetag) {
        this.isolanguagetag = isolanguagetag;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}