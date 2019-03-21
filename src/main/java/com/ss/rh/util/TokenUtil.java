package com.ss.rh.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ss.rh.constants.ConfigProperties;
import com.ss.rh.constants.Constants;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenUtil {
    public static final Logger logger = LoggerFactory.getLogger(TokenUtil.class);

    @Autowired
    ConfigProperties configProperties;

    public String createToken(String id) {
        String token = null;

        Date now = new Date();
        String time = Long.toString(now.getTime());

        try {
            token = JWT.create().withAudience(id).sign(Algorithm.HMAC256(Constants.TOKEN_SECRET));
//            token = JWT.create().withAudience(id+time).withExpiresAt(new Date()).sign(Algorithm.HMAC256(Constants.TOKEN_SECRET));
        }
        catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        return token;
    }

    public Map getSessionByJsCode(String code) {
        Map<String, Object> params = new HashMap<>();
        params.put("appid", configProperties.getMpAppId());
        params.put("secret", configProperties.getMpSecret());
        params.put("js_code", code);
//        logger.info(code);
        params.put("grant_type", "authorization_code");
        String accessTokenResponse = HttpUtil.get(configProperties.getAccessTokenUrl(), params);

        logger.info("accessTokenResponse : " + accessTokenResponse);
//        System.out.println("accessTokenResponse : " + accessTokenResponse);

        return JsonUtil.json2Map(accessTokenResponse);
    }

}
