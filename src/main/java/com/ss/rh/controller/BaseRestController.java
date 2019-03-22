package com.ss.rh.controller;

import com.ss.rh.constants.ConfigProperties;
import com.ss.rh.constants.Constants;
import com.ss.rh.entity.User;
import com.ss.rh.util.JsonUtil;
import com.ss.rh.util.RedisCacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class BaseRestController {


    @Autowired
    private RedisCacheUtil redisCacheUtil;

    @Autowired
    private ConfigProperties configProperties;

    /*
    获取token（在header和attribute中找）
     */
    protected String getUserToken() {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String hToken = request.getHeader(configProperties.getWX_TOKEN_NAME());

        if (hToken != null) {
            return hToken;
        }

        return (String) request.getAttribute(configProperties.getWX_TOKEN_NAME());

    }

    /*
    获取当前用户
     */
    protected User getSessionUser() {
        String userStr = redisCacheUtil.get(getUserToken());

        if (!userStr.isEmpty())
            return JsonUtil.json2Object(userStr, User.class);

        return null;
    }

    /*
    将用户与token绑定
     */
    protected void saveUser(User user) {

//        System.out.println(Integer.toString(user.getId()));
        String uid = redisCacheUtil.get(Integer.toString(user.getId()));

        redisCacheUtil.set(uid, JsonUtil.object2JsonStr(user));
    }

    /*
    将用户token存入redis并设置过期时间
     */
    protected void saveSession(int id, String token) {
        redisCacheUtil.setExpire(Integer.toString(id), token, configProperties.getUserExpireTime());
    }

}
