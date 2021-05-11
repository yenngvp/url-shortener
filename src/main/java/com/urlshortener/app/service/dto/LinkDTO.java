package com.urlshortener.app.service.dto;

import com.urlshortener.app.model.Link;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * A DTO representing a link, with only the public attributes.
 */
public class LinkDTO implements Serializable {
    private final static Long serialVersionUID = 1829378689L;

    private UUID id;
    private String shortUrl;
    private String url;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public LinkDTO() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static LinkDTO fromLink(Link link) {
        if (link == null) {
            return null;
        }

        LinkDTO linkDTO = new LinkDTO();
        linkDTO.setId(link.getId());
        linkDTO.setUrl(link.getUrl());
        linkDTO.setShortUrl(link.getShortUrl());
        linkDTO.setCreatedAt(link.getCreatedAt());
        linkDTO.setUpdatedAt(link.getUpdatedAt());

        return linkDTO;
    }
}
