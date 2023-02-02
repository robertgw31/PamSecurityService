package com.senutech.pam.security.app.controller;

import com.senutech.pam.security.app.model.container.ApiErrorResponse;
import com.senutech.pam.security.app.exception.PamException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorMessage = "Malformed JSON request";
        logger.error(errorMessage);
        return buildResponseEntity(new ApiErrorResponse(HttpStatus.BAD_REQUEST, errorMessage, ex));
    }

    //other exception handlers

    @ExceptionHandler(PamException.class)
    protected ResponseEntity<Object> handleEntityNotFound(PamException ex) {
        ApiErrorResponse restError = new ApiErrorResponse(ex);
        return buildResponseEntity(restError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiErrorResponse errorResponse) {
        HttpStatus status = errorResponse.getStatus();
        return new ResponseEntity<>(errorResponse, status);
    }
}