package com.senutech.pam.security.app.model.auth0;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Auth0UserIdentity {
    @JsonProperty("user_id")
    private String userId;
    private String provider;
    private String connection;
    private boolean isSocial;
}
