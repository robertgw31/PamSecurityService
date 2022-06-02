package com.senutech.pam.security.app.model.domain;

import javax.persistence.*;

@Entity
@Table(name = "controls")
public class Control {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "envid", nullable = false, length = 50)
    private String envid;

    @Column(name = "envname", nullable = false, length = 200)
    private String envname;

    @Lob
    @Column(name = "federationid")
    private String federationid;

    @Column(name = "passwordkey")
    private byte[] passwordkey;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnvid() {
        return envid;
    }

    public void setEnvid(String envid) {
        this.envid = envid;
    }

    public String getEnvname() {
        return envname;
    }

    public void setEnvname(String envname) {
        this.envname = envname;
    }

    public String getFederationid() {
        return federationid;
    }

    public void setFederationid(String federationid) {
        this.federationid = federationid;
    }

    public byte[] getPasswordkey() {
        return passwordkey;
    }

    public void setPasswordkey(byte[] passwordkey) {
        this.passwordkey = passwordkey;
    }

}