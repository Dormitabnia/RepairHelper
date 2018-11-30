package com.ss.rh.constants;

import org.springframework.beans.factory.annotation.Value;

public class Constants {

    public static String TOKEN_SECRET = "TOKEN_SECRET_WK";

    public static int ADMIN = 0;  //系统管理员
    public static int REPAIRER = 1;  //维修人员
    public static int ORDERER = 2;  //开立报修人员
    public static int ORDINARY = 3;  //普通客户

    @Value("${wechat.mp.app-id}")
    public static String mpAppId;

    @Value("${wechat.mp.app-secret}")
    public static String mpSecret;

    @Value("${wechat.accessTokenUrl}")
    public static String accessTokenUrl;

    @Value("${wechat.hToken.name}")
    public static String WX_TOKEN_NAME;

}
