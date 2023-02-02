package com.senutech.pam.security.app.model.auth0;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Auth0AccessPass {

    @JsonProperty("access_token")
    String accessToken;
    String scope;

    @JsonIgnore
    private OffsetDateTime timestamp = OffsetDateTime.now();

    @JsonProperty("expires_in")
    long expiresIn;

    @JsonProperty("token_type")
    String tokenType;

    @JsonIgnore
    public OffsetDateTime getEpirationTime() {
        return timestamp.plusSeconds(expiresIn);
    }

    @JsonIgnore
    public boolean isExpired() {
        return OffsetDateTime.now().isAfter(getEpirationTime());
    }
}
