package com.senutech.pam.security.app.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "controls")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Control {
    @Id
    @Column(name = "id", columnDefinition = "uuid", nullable = false, updatable = false)
    private Integer id;

    @Column(name = "envid", nullable = false, length = 50)
    private String envid;

    @Column(name = "envname", nullable = false, length = 200)
    private String envname;


    @Column(name = "federationid")
    private String federationid;

    @Column(name = "passwordkey")
    private byte[] passwordkey;

}