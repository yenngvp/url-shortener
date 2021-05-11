package com.urlshortener.app.service.dto;

import java.io.Serializable;

public class RevertLinkDTO implements Serializable {
    private final static Long serialVersionUID = 182937194L;

    private String shortUrl;

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
