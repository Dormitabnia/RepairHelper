package com.ss.rh.constants;

import org.springframework.beans.factory.annotation.Value;

public class Constants {

    @Value("${wechat.mp.app-id}")
    public static String mpAppId;

    @Value("${wechat.mp.app-secret}")
    public static String mpSecret;

    @Value("${wechat.accessTokenUrl}")
    public static String accessTokenUrl;

}
