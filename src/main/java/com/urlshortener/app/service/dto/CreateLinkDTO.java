package com.urlshortener.app.service.dto;

import java.io.Serializable;

public class CreateLinkDTO implements Serializable {
    private final static Long serialVersionUID = 182937192L;

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
