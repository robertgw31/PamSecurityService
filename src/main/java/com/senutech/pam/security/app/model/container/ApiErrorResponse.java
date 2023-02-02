package com.senutech.pam.security.app.model.container;

import com.senutech.pam.security.app.exception.PamException;
import com.senutech.pam.security.app.exception.ValidationError;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ApiErrorResponse extends ApiResponseBase {
    private HttpStatus status;

    private OffsetDateTime timestamp;
    private String errorId;
    private String message;
    private String debugMessage;
    private List<ValidationError> subErrors = new ArrayList<>();

    private ApiErrorResponse() {
        super(false);
        timestamp = OffsetDateTime.now(ZoneOffset.UTC);
    }

    public ApiErrorResponse(HttpStatus status) {
        this();
        this.status = status;
    }

    public ApiErrorResponse(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    public ApiErrorResponse(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    public ApiErrorResponse(PamException ex) {
        super(false);
        if (ex.isClientDataError()) {
            this.status = HttpStatus.BAD_REQUEST;
        } else {
            this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        this.timestamp = ex.getTimestamp();
        this.errorId = ex.getId();
        this.message = ex.getUserMessage();
        this.debugMessage = ex.getMessage();
        this.subErrors.addAll(ex.getValidationErrors());
    }

}
