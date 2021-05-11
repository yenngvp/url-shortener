package com.urlshortener.app.repository;

import com.urlshortener.app.model.Setting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SettingRepository extends JpaRepository<Setting, UUID> {
    Setting findTopByUserId(UUID userId);
}
