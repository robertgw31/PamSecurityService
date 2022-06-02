package com.senutech.pam.security.app.exception;

import com.senutech.pam.security.app.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.UUID;



public class PamException extends Exception {
    private static Logger logger = LoggerFactory.getLogger(PamException.class);

    private String id = UUID.randomUUID().toString().toUpperCase();
    private OffsetDateTime timestamp = OffsetDateTime.now(ZoneOffset.UTC);
    private String userMessage = String.format("Exception ID %s",id.toString());
    private String objectContext;


    public PamException(String message) {

        super(message);
    }
    public PamException(String message, Throwable rootCause) {
        super(message,rootCause);
        log(this);
    }
    public PamException(String message,Object objCOntext) {
        super(message);
        this.userMessage = userMessage;
        this.objectContext = createObjectContext(objCOntext);
        log(this);
    }

    public PamException(String message, Object objCOntext, Throwable rootCause) {
        super(message,rootCause);
        this.userMessage = userMessage;
        this.objectContext = createObjectContext(objCOntext);
        log(this);
    }

    public String getId() { return id;}
    public OffsetDateTime getTimestamp() { return timestamp;}
    public String getUserMessage() { return userMessage;}

    public String getObjectContext() { return objectContext;}


    private static void log(PamException ex) {
        String msg = String.format("Exception ID: %s: %s\n%s\n\n",ex.id.toString(),ex.getMessage(),ex.objectContext );
        logger.error(msg,ex);
    }

    public static PamException normalize(String message, Throwable ex) {
        PamException pe = null;
        if(ex instanceof PamException) {
            pe = (PamException) ex;
        } else {
            String newMessage = String.format("%s - %s",message, ex.getMessage());
            pe = new PamException(newMessage,ex);
        }
        return pe;
    }
    public static PamException normalize(String message, Object context, Throwable ex) {
        PamException pe = null;
        if(ex instanceof PamException) {
            pe = (PamException) ex;
        } else {
            pe = new PamException(message,context,ex);
        }
        return pe;
    }

    public static PamException normalize(String message, Object context) {
        PamException pe = null;
        pe = new PamException(message,context);
        return pe;
    }

    private static String createObjectContext(Object obj) {
        String jsonSerializedObject = JsonUtil.objectToPrettyJson(obj);
        return String.format("%s: %s",obj.getClass().getCanonicalName(), jsonSerializedObject);
    }
}
