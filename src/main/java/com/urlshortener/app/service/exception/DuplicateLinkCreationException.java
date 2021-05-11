package com.urlshortener.app.service.exception;

public class DuplicateLinkCreationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DuplicateLinkCreationException() {
        super("Request to create a duplicate link!");
    }
}
