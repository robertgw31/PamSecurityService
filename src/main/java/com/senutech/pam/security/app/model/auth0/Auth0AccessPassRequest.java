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
public class Auth0AccessPassRequest {

    @JsonProperty("client_id")
    private String clientId;
    @JsonProperty("client_secret")
    private String clientSecret;
    private String audience;
    @JsonProperty("grant_type")
    private String grantType;

    /*
      client_id: this.MGMT_API_CLIENT_ID,
            client_secret: this.MGMT_API_CLIENT_SECRET,
            audience: "https://senutech.us.auth0.com/api/v2/",
            grant_type: "client_credentials"
     */
}
