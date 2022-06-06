package com.senutech.pam.security.app.exception;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ValidationError extends ExceptionDetail {
    private String objectContext;
    private String fieldName;
    private Object rejectedValue;
    private String message;

    public ValidationError(String objectContext, String message) {
        this.objectContext = objectContext;
        this.message = message;
    }

    public ValidationError(String objectContext, String fieldName, String message, Object rejectedValue) {
        this.objectContext = objectContext;
        this.fieldName = fieldName;
        this.message = message;
        this.rejectedValue = rejectedValue;
    }
}