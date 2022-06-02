package com.senutech.pam.security.app.model.containers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountCreateRequest {

    // these from the client
    private OffsetDateTime userLocalDateTime;
    private String isoLanguage;
    private String isoCountry;
    private String accountName;
    private String loginFullName;
    private String loginEmail;
    private String loginPassword;
    private String loginAuthProvider;
    private String loginImageURL;


    // these at the application boundry
    private String clientMachine;
    private OffsetDateTime requestRecieptTime;
    private String EmailVerificationUrlRoot;

    // these are set within the service
    private UUID id;
    private OffsetDateTime timestamp;
    private OffsetDateTime openDateTime;


}
