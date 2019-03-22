package com.ss.rh.util;

//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/*
发起http请求的工具类
 */
public class HttpUtil {

    public static String get(String url, Map<String, Object> param) {
        RestTemplate restTemplate = new RestTemplate();

        // 不清楚为什么传入HttpEntity后，微信的接口会显示无效appid，而使用直接拼接url是有效的
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//        HttpEntity entity = new HttpEntity<>(param, headers);
//        System.out.println(entity);
        url += "?";
        for (String key : param.keySet()) {
            String value = (String)param.get(key);
            url += key + "=" + value;
            url += "&";
        }
        url.substring(0, url.length() - 1);
        String resultBody = restTemplate.exchange(url, HttpMethod.GET, null, String.class).getBody();

        return resultBody;
    }

}
