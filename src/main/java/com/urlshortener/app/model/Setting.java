package com.urlshortener.app.model;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "setting")
public class Setting implements Serializable {
    @Id
    private UUID id;
    private String domain;
    private UUID userId;

    public Setting() {
    }

    public Setting(UUID id, String domain, UUID userId) {
        this.id = id;
        this.domain = domain;
        this.userId = userId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
