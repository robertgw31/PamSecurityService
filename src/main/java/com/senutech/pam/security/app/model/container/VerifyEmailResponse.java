package com.senutech.pam.security.app.model.container;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VerifyEmailResponse {
    boolean success;
    boolean exists;
    String message;
}
