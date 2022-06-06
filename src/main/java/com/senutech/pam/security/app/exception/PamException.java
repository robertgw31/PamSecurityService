package com.senutech.pam.security.app.exception;

import com.senutech.pam.security.app.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;



public class PamException extends Exception {
    private static final Logger logger = LoggerFactory.getLogger(PamException.class);

    private final String id = UUID.randomUUID().toString().toUpperCase();
    private final OffsetDateTime timestamp = OffsetDateTime.now(ZoneOffset.UTC);
    private boolean clientDataError = false;
    private String userMessage = "";
    private String objectContext;
    private final List<ValidationError> validationErrors = new ArrayList<ValidationError>();


    public PamException(String message) {
        super(message);
    }
    public PamException(String message, String userMessage) {
        super(message);
        this.userMessage = userMessage;
        this.clientDataError = true;
    }
    public PamException(String userMessage,List<ValidationError> details) {
        super("Validation Errors");
        this.userMessage = userMessage;
        if (details != null && details.size() >0) {
            this.validationErrors.addAll(details);
            clientDataError = true;
        }
    }

    public PamException(String message, Throwable rootCause) {
        super(message,rootCause);
        log(this);
    }
    public PamException(String message,Object objCOntext) {
        super(message);
        this.objectContext = createObjectContext(objCOntext);
        log(this);
    }

    public PamException(String message, Object objCOntext, Throwable rootCause) {
        super(message,rootCause);
        this.objectContext = createObjectContext(objCOntext);
        log(this);
    }

    public String getId() { return id;}
    public OffsetDateTime getTimestamp() { return timestamp;}
    public List<ValidationError> getValidationErrors() { return validationErrors;}
    public String getUserMessage() { return userMessage;}
    public boolean isClientDataError() { return this.clientDataError;}

    public String getObjectContext() { return objectContext;}


    private static void log(PamException ex) {
        String msg = String.format("Exception ID: %s: %s\n%s\n\n",ex.id,ex.getMessage(),ex.objectContext );
        logger.error(msg,ex);
    }

    public static PamException normalize(String message, Throwable ex) {
        PamException pe;
        if(ex instanceof PamException) {
            pe = (PamException) ex;
        } else {
            String newMessage = String.format("%s - %s",message, ex.getMessage());
            pe = new PamException(newMessage,ex);
        }
        return pe;
    }
    public static PamException normalize(String message, Object context, Throwable ex) {
        PamException pe;
        if(ex instanceof PamException) {
            pe = (PamException) ex;
        } else {
            pe = new PamException(message,context,ex);
        }
        return pe;
    }

    public static PamException normalize(String message, Object context) {
        PamException pe;
        pe = new PamException(message,context);
        return pe;
    }

    private static String createObjectContext(Object obj) {
        String jsonSerializedObject = JsonUtil.objectToPrettyJson(obj);
        return String.format("%s: %s",obj.getClass().getCanonicalName(), jsonSerializedObject);
    }
}
