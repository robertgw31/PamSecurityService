package com.senutech.pam.security.app.model.auth0;


/*
{
        "created_at": "2023-01-24T16:13:36.440Z",
        "email": "robertw@senutech.com",
        "email_verified": false,
        "identities": [
            {
                "user_id": "63d003b044dabb47a05ee6a3",
                "provider": "auth0",
                "connection": "Username-Password-Authentication",
                "isSocial": false
            }
        ],
        "name": "robertw@senutech.com",
        "nickname": "robertw",
        "picture": "https://s.gravatar.com/avatar/1d0e7a74d2a61cf425c95149a21f1a24?s=480&r=pg&d=https%3A%2F%2Fcdn.auth0.com%2Favatars%2Fro.png",
        "updated_at": "2023-02-02T14:56:52.734Z",
        "user_id": "auth0|63d003b044dabb47a05ee6a3",
        "user_metadata": {
            "geoip": {
                "country_code": "US",
                "country_code3": "USA",
                "country_name": "United States",
                "city_name": "Port Saint Lucie",
                "latitude": 27.2913,
                "longitude": -80.2977,
                "time_zone": "America/New_York",
                "continent_code": "NA",
                "subdivision_code": "FL",
                "subdivision_name": "Florida"
            }
        },
        "last_login": "2023-02-01T21:48:05.418Z",
        "last_ip": "2601:58b:4101:ab80:cd81:afa0:cd20:b311",
        "logins_count": 14
    }
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Auth0User {
    /*
        "created_at": "2023-01-24T16:13:36.440Z",
        "email": "robertw@senutech.com",
        "email_verified": false,
   "name": "robertw@senutech.com",
        "nickname": "robertw",
        "picture": "https://s.gravatar.com/avatar/1d0e7a74d2a61cf425c95149a21f1a24?s=480&r=pg&d=https%3A%2F%2Fcdn.auth0.com%2Favatars%2Fro.png",
        "updated_at": "2023-02-02T14:56:52.734Z",
        "user_id": "auth0|63d003b044dabb47a05ee6a3",
            "last_login": "2023-02-01T21:48:05.418Z",
        "last_ip": "2601:58b:4101:ab80:cd81:afa0:cd20:b311",
        "logins_count": 14
        identities
        user_metadata
     */

    @JsonProperty("user_id")
    private String userId;
    private String name;
    private String nickName;
    private String email;
    @JsonProperty("email_verified")
    private boolean emailVerified;
    @JsonProperty("picture")
    private String pictureUrl;

    @JsonProperty("created_at")
    private OffsetDateTime createdAt;
    @JsonProperty("updated_at")
    private OffsetDateTime updatedAt;
    @JsonProperty("last_login")
    private OffsetDateTime lastLogin;
    @JsonProperty("logins_count")
    private long NumberOfLogins;
    @JsonProperty("last_ip")
    private String lastIP;

    private List<Auth0UserIdentity> identities;

    @JsonProperty("user_metadata")
    private Auth0UserMetaData metaData;
}
