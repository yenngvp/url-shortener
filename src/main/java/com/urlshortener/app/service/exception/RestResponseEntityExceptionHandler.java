package com.urlshortener.app.service.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {
            DuplicateLinkCreationException.class,
            LinkNotFoundException.class,
            UserSettingNotFoundException.class,
            URLConflictRoundCheckLimitException.class
    })
    protected ResponseEntity<Object> handleMyException(RuntimeException ex, WebRequest request) {
        String responseBody = ex.getMessage();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(ex, responseBody, headers, HttpStatus.BAD_REQUEST, request);
    }
}