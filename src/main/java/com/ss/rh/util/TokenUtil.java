package com.ss.rh.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ss.rh.constants.Constants;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;

public class TokenUtil {
    public static final Logger logger = LoggerFactory.getLogger(TokenUtil.class);

    public static String createToken(String id) {
        String token = null;

        try {
            token = JWT.create().withAudience(id).sign(Algorithm.HMAC256(Constants.TOKEN_SECRET));
        }
        catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        return token;
    }

    public static Map getSessionByJsCode(String code) {
        Map<String,Object> params = new HashMap<>();
        params.put("appid", Constants.mpAppId);
        params.put("secret", Constants.mpSecret);
        params.put("js_code", code);
        params.put("grant_type", "authorization_code");
        String accessTokenResponse = HttpUtil.get(Constants.accessTokenUrl, params);

        logger.info("accessTokenResponse : " + accessTokenResponse);
//        System.out.println("accessTokenResponse : " + accessTokenResponse);

        return JsonUtil.json2Map(accessTokenResponse);
    }

}
