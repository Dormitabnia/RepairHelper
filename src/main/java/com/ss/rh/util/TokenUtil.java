package com.ss.rh.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ss.rh.constants.Constants;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class TokenUtil {

    public static String createToken(String id) throws UnsupportedEncodingException {
        String token;
        token = JWT.create().withAudience(id).sign(Algorithm.HMAC256(Constants.TOKEN_SECRET));

        return token;
    }

    public static Map getSessionByJsCode(String code) {
        Map<String,Object> params = new HashMap<>();
        params.put("appid", Constants.mpAppId);
        params.put("secret", Constants.mpSecret);
        params.put("js_code", code);
        params.put("grant_type", "authorization_code");
        String accessTokenResponse = HttpUtil.get(Constants.accessTokenUrl, params);

        System.out.println("accessTokenResponse : " + accessTokenResponse);

        return JsonUtil.json2Map(accessTokenResponse);
    }

}
