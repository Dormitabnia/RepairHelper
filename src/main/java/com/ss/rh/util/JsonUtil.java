package com.ss.rh.util;

import com.alibaba.fastjson.JSONObject;

public class JsonUtil {

    public static String success(String msg) {
        JSONObject json = new JSONObject();
        json.put("success", true);
        json.put("msg", msg);

        return json.toJSONString();
    }

    public static String success(String msg, Object object) {
        JSONObject json = new JSONObject();
        json.put("success", true);
        json.put("msg", msg);
        json.put("rs", object);

        return json.toJSONString();
    }

    public static String failure(String msg) {
        JSONObject json = new JSONObject();
        json.put("success", false);
        json.put("msg", msg);

        return json.toJSONString();
    }
}
