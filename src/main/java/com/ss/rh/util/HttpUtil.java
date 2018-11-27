package com.ss.rh.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class HttpUtil {

    public static String get(String url, Map param) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity entity = new HttpEntity<>(param, headers);
        String resultBody = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();

        return resultBody;
    }

}
