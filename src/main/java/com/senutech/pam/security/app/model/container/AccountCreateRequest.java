package com.senutech.pam.security.app.model.container;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountCreateRequest {

    // these from the client

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "UTC")
    private OffsetDateTime userLocalDateTime;
    private String isoLanguage;
    private String isoCountry;
    private String accountName;
    private String loginFullName;
    private String loginEmail;
    private String loginPassword;
    private String loginAuthProvider;
    private String loginImageURL;
    private String  emailVerificationUrl;


    // these at the application boundry
    private String clientMachine;

    private OffsetDateTime requestRecieptTime;


    // these are set within the service
    private UUID id;

    private OffsetDateTime timestamp;
    private OffsetDateTime openDateTime;


}
