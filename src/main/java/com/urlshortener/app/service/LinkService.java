package com.urlshortener.app.service;

import com.urlshortener.app.config.Constant;
import com.urlshortener.app.model.Link;
import com.urlshortener.app.model.Setting;
import com.urlshortener.app.repository.LinkRepository;
import com.urlshortener.app.repository.SettingRepository;
import com.urlshortener.app.service.exception.DuplicateLinkCreationException;
import com.urlshortener.app.service.exception.LinkNotFoundException;
import com.urlshortener.app.service.exception.URLConflictRoundCheckLimitException;
import com.urlshortener.app.service.exception.UserSettingNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.urlshortener.app.config.Constant.*;

@Service
public class LinkService {
    private final LinkRepository linkRepository;
    private final SettingRepository settingRepository;

    @Autowired
    public LinkService(LinkRepository linkRepository, SettingRepository settingRepository) {
        this.linkRepository = linkRepository;
        this.settingRepository = settingRepository;
    }

    public List<Link> findAll() {
        return this.linkRepository.findAllByOrderByCreatedAtDesc();
    }

    public Link generateShortLink(String url) {
        Link link = new Link();
        link.setShortUrl(this.validateAndGenerateUniqueShortUrl(url));
        link.setUrl(url);
        link.setUserId(Constant.FAKE_USER_ID);
        return this.linkRepository.save(link);
    }

    public Link revertShortLink(String shortUrl) {
        return Optional
                .ofNullable(this.linkRepository.findByShortUrl(shortUrl))
                .orElseThrow(LinkNotFoundException::new);
    }

    public void deleteLink(UUID id) {
        if (!this.linkRepository.existsById(id)) {
            throw new LinkNotFoundException();
        }

        this.linkRepository.deleteById(id);
    }

    private String validateAndGenerateUniqueShortUrl(String url) {
        UUID userId = Constant.FAKE_USER_ID;
        Setting userSetting = Optional
                .ofNullable(this.settingRepository.findTopByUserId(userId))
                .orElseThrow(UserSettingNotFoundException::new);

        if (this.linkRepository.existsByUrl(url)) {
            throw new DuplicateLinkCreationException();
        }

        // TODO: load and save this value to database for more efficiency
        int randomByteLength = MIN_SHORT_URL_ID_LENGTH - 1;
        int round = 0;
        do {
            String randomString = this.generateRandomString(randomByteLength);
            String shortUrl = userSetting.getDomain() + "/" + randomString;
            if (!this.linkRepository.existsByShortUrl(shortUrl)) {
                return shortUrl;
            }

            round++;
            if (round > MAX_URL_CONFLICT_ROUND_CHECK_RATE) {
                throw new URLConflictRoundCheckLimitException();
            }

            if (round > MAX_URL_CONFLICT_ROUND_CHECK_PER_LENGTH_RATE) {
                randomByteLength++;
            }
        } while (true);
    }

    /**
     * Generate a random URL-safe string with desired length
     *
     * @param byteLength
     * @return a random string token
     */
    private String generateRandomString(int byteLength) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] token = new byte[byteLength];
        secureRandom.nextBytes(token);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(token);
    }
}
