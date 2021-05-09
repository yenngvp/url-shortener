package com.urlshortener.app.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.AllArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Index;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Link", indexes = @Index(columnList = "shortUrl", unique = true))
public class Link {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    @NonNull
    private String shortUrl;
    @NonNull
    private String url;
    @NonNull
    private Date createdDate;
}
