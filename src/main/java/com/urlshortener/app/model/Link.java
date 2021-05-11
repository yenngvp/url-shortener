package com.urlshortener.app.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "link",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"shortUrl"}),
                @UniqueConstraint(columnNames = {"url"}),
                @UniqueConstraint(columnNames = {"shortUrl", "url"})
        }
)
public class Link implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String shortUrl;
    private String url;
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;
    private UUID userId;

    public Link(UUID id, String shortUrl, String url, Timestamp createdAt, Timestamp updatedAt, UUID userId) {
        this.id = id;
        this.shortUrl = shortUrl;
        this.url = url;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userId = userId;
    }

    public Link() {
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

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
