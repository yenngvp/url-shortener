package com.urlshortener.app.service.exception;

public class LinkNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public LinkNotFoundException() {
        super("Link not found.");
    }
}
