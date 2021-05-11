package com.urlshortener.app.web.rest;

import com.urlshortener.app.model.Setting;
import com.urlshortener.app.service.SettingService;
import com.urlshortener.app.service.dto.SettingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class SettingController {
    private final SettingService settingService;

    @Autowired
    public SettingController(SettingService settingService) {
        this.settingService = settingService;
    }

    @GetMapping("/settings")
    public Collection<Setting> getSettings() {
        return this.settingService.findAll();
    }

    @PostMapping("/settings")
    public SettingDTO updateSetting(@RequestBody SettingDTO settingDTO) {
        return SettingDTO.fromSetting(this.settingService.updateSetting(settingDTO.getDomain()));
    }
}
