package com.urlshortener.app.service.exception;

public class UserSettingNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UserSettingNotFoundException() {
        super("User setting not found.");
    }
}
