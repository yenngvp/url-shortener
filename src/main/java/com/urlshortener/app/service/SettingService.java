package com.urlshortener.app.service;

import com.urlshortener.app.config.Constant;
import com.urlshortener.app.model.Setting;
import com.urlshortener.app.repository.SettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SettingService {
    private final SettingRepository settingRepository;

    @Autowired
    public SettingService(SettingRepository settingRepository) {
        this.settingRepository = settingRepository;
    }

    public List<Setting> findAll() {
        return this.settingRepository.findAll();
    }

    public Setting updateSetting(String domain) {
        Setting existingSetting = this.settingRepository.findTopByUserId(Constant.FAKE_USER_ID);
        if (existingSetting == null) {
            existingSetting = new Setting();
            existingSetting.setUserId(Constant.FAKE_USER_ID);
            existingSetting.setId(UUID.randomUUID());
        }
        existingSetting.setDomain(domain);
        return this.settingRepository.save(existingSetting);
    }
}
