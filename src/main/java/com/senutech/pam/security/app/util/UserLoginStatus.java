package com.senutech.pam.security.app.util;

public enum UserLoginStatus {
    PENDING_EMAIL_VERIFICATION,
    ACTIVE,
    LOGIN_FAILURE_SUSPENDED,
    ADMIN_SUSPENDED,
    CLOSED,
    PRE_PURGED

}
