package com.senutech.pam.security.app.model.domain;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "financialtransactionsource")
public class Financialtransactionsource {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "code", nullable = false, length = 100)
    private String code;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}