package com.senutech.pam.security.app.model.domain;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "parameter")
public class Parameter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "environment", nullable = false, length = 50)
    private String environment;

    @Column(name = "javatype", length = 200)
    private String javatype;

    @Column(name = "friendlyname", nullable = false, length = 200)
    private String friendlyname;

    @Lob
    @Column(name = "thevalue", nullable = false)
    private String thevalue;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getJavatype() {
        return javatype;
    }

    public void setJavatype(String javatype) {
        this.javatype = javatype;
    }

    public String getFriendlyname() {
        return friendlyname;
    }

    public void setFriendlyname(String friendlyname) {
        this.friendlyname = friendlyname;
    }

    public String getThevalue() {
        return thevalue;
    }

    public void setThevalue(String thevalue) {
        this.thevalue = thevalue;
    }

}