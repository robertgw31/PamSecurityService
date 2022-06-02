package com.senutech.pam.security.app.model.domain;

import javax.persistence.*;

@Entity
@Table(name = "supportedlang")
public class Supportedlang {
    @Id
    @Column(name = "isolanguage", nullable = false, length = 20)
    private String id;

    @Column(name = "iconcls", length = 100)
    private String iconcls;

    @Lob
    @Column(name = "thevalue", nullable = false)
    private String thevalue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIconcls() {
        return iconcls;
    }

    public void setIconcls(String iconcls) {
        this.iconcls = iconcls;
    }

    public String getThevalue() {
        return thevalue;
    }

    public void setThevalue(String thevalue) {
        this.thevalue = thevalue;
    }

}