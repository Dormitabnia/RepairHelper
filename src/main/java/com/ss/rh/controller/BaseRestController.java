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
        String uid = redisCacheUtil.get(getUserToken());

        if (uid != null)
            return JsonUtil.json2Object(redisCacheUtil.get(uid), User.class);

        return null;
    }

    /*
    将用户信息缓存到redis
     */
    protected void saveUser(User user) {

//        System.out.println(Integer.toString(user.getId()));
//        String token = redisCacheUtil.get(Integer.toString(user.getId()));
        redisCacheUtil.setExpire(Integer.toString(user.getId()), JsonUtil.object2JsonStr(user), configProperties.getUserExpireTime());
    }

    /*
    将用户token存入redis并设置过期时间（token作为key）
     */
    protected void saveSession(int id, String token) {
        redisCacheUtil.setExpire(token, Integer.toString(id), configProperties.getUserExpireTime());
    }

    protected void delSession(String token) {
        redisCacheUtil.delete(token);
    }

}
