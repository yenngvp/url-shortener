package com.urlshortener.app.service.exception;

public class URLConflictRoundCheckLimitException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public URLConflictRoundCheckLimitException() {
        super("Maximum number of URL conflict round checking limit reached.");
    }
}
