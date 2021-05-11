package com.urlshortener.app.repository;

import com.urlshortener.app.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LinkRepository extends JpaRepository<Link, UUID> {
    List<Link> findAllByOrderByCreatedAtDesc();
    Link findByShortUrl(String shortUrl);
    Link findByUrl(String url);
    boolean existsByShortUrl(String shortUrl);
    boolean existsByUrl(String url);
}
