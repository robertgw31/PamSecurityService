package com.senutech.pam.security.app.model.auth0;

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
public class Auth0ApiResponse {
    private String type;
    private String status;
    @JsonProperty("created_at")
    private OffsetDateTime createdAt;
    private String id;

}
