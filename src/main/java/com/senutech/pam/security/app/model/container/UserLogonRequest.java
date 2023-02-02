package com.senutech.pam.security.app.model.container;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLogonRequest {
    String email;
    boolean emailVerified;
    String accessToken;
    String nickName;
    String givenName;
    String familyName;
    String clientMachine;
}
