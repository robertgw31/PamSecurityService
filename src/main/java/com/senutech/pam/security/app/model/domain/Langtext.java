package com.senutech.pam.security.app.model.domain;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "langtext")
public class Langtext {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "isolanguage", nullable = false, length = 10)
    private String isolanguage;

    @Column(name = "mnumonic", nullable = false, length = 100)
    private String mnumonic;

    @Lob
    @Column(name = "thevalue", nullable = false)
    private String thevalue;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getIsolanguage() {
        return isolanguage;
    }

    public void setIsolanguage(String isolanguage) {
        this.isolanguage = isolanguage;
    }

    public String getMnumonic() {
        return mnumonic;
    }

    public void setMnumonic(String mnumonic) {
        this.mnumonic = mnumonic;
    }

    public String getThevalue() {
        return thevalue;
    }

    public void setThevalue(String thevalue) {
        this.thevalue = thevalue;
    }

}