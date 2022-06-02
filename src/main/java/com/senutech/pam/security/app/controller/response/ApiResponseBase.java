package com.senutech.pam.security.app.controller.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponseBase {
    public boolean success;

    public ApiResponseBase() {
        this.success = true;
    }
    public ApiResponseBase(boolean success) {
        this.success = success;
    }
}
