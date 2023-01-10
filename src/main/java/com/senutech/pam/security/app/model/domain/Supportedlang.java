package com.senutech.pam.security.app.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "supportedlang")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Supportedlang {
    @Id
    @Column(name = "isolanguage", length = 20, nullable = false, updatable = false)
    private String isoLanguage;

    @Column(name = "iconcls", length = 100)
    private String iconcls;

    @Column(name = "thevalue", nullable = false)
    private String thevalue;

}