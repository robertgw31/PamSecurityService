package com.senutech.pam.security.app.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.senutech.pam.security.app.exception.ExceptionDetail;
import com.senutech.pam.security.app.exception.PamException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Getter
@Setter
public class ApiErrorResponse extends ApiResponseBase {
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private OffsetDateTime timestamp;
    private String errorId;
    private String message;
    private String debugMessage;
    private List<ExceptionDetail> subErrors;

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
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.timestamp = ex.getTimestamp();
        this.errorId = ex.getId();
        this.message = ex.getUserMessage();
    }

}
