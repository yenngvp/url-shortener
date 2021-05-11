package com.urlshortener.app.config;

import com.urlshortener.app.model.Setting;
import com.urlshortener.app.repository.SettingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(SettingRepository repository) {
        return args -> {
            // Create a sample setting record
            log.info("Preloading 'setting' " +
                    repository.save(new Setting(
                            UUID.fromString("09725810-ee47-4a48-977d-0ac9fdc39292"),
                            "http://localhost",
                            Constant.FAKE_USER_ID)
                    )
            );
        };
    }
}