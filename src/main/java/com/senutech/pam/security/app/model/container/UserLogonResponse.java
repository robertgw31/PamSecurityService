package com.senutech.pam.security.app.model.container;

import com.senutech.pam.security.app.model.domain.Userlogin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLogonResponse extends  ApiResponseBase {
    private boolean logonExists = true;
    private Userlogin userLogin;
    private String sessionId;
    private String accessToken;
}
