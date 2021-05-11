package com.urlshortener.app.service.dto;

import com.urlshortener.app.model.Setting;

import java.io.Serializable;

public class SettingDTO implements Serializable {
    private final static Long serialVersionUID = 182937196L;

    private String domain;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public static SettingDTO fromSetting(Setting setting) {
        SettingDTO dto = new SettingDTO();
        dto.setDomain(setting.getDomain());
        return dto;
    }
}
