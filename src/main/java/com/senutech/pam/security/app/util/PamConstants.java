package com.senutech.pam.security.app.util;

public class PamConstants {
    public static final String WEB_APP_PATH_PREFIX = "w";
    public static final String WEB_APP_PATH_EMAIL_VERIFICTION = "emailverification";
    public static final int MAX_FAILED_LOGIN_ATTEMPTS = 3;
    public static final int MAX_MINUTES_FOR_EMAIL_VERIFICATION = 30;

    public static final String USER_LOGON_STATUS_PENDING_EMAIL_VERIFCATION = "PENDING_EMAIL_VERIFICATION";
    public static final String USER_LOGON_STATUS_ACTIVE = "ACTIVE";
    public static final String USER_LOGON_STATUS_LOGIN_FAILED_LOCK = "LOGON_FAILED_LOCK";
    public static final String USER_LOGON_STATUS_CLOSED = "CLOSED";
    public static final String USER_LOGON_STATUS_SUSPENDED = "SUSPENDED";


    public static final String AUTH0_API_OP_STATUS_PENDING = "pending";
    public static final String AUTH0_API_OP_STATUS_COMPLETED = "completed";
}
