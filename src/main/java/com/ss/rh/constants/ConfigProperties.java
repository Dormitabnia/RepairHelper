package com.ss.rh.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigProperties {
    @Value("${wechat.mp.app-id}")
    private String mpAppId;

    @Value("${wechat.mp.app-secret}")
    private String mpSecret;

    @Value("${wechat.accessTokenUrl}")
    private String accessTokenUrl;

    @Value("${wechat.hToken.name}")
    private String WX_TOKEN_NAME;

    @Value("${file.rootFolderName}")
    private String rootPath;

    @Value("${wechat.userExpireTime}")
    private int userExpireTime;

    @Value("${backend.token-name}")
    private String BK_TOKEN_NAME;

    public String getMpAppId() {
        return mpAppId;
    }

    public String getMpSecret() {
        return mpSecret;
    }

    public String getAccessTokenUrl() {
        return accessTokenUrl;
    }

    public String getWX_TOKEN_NAME() {
        return WX_TOKEN_NAME;
    }

    public String getRootPath() {
        return rootPath;
    }

    public int getUserExpireTime() {
        return userExpireTime;
    }

    public String getBK_TOKEN_NAME() {
        return BK_TOKEN_NAME;
    }
}
