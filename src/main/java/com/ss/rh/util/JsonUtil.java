package com.ss.rh.util;

import com.alibaba.fastjson.JSONObject;

public class JsonUtil {

    public static String success(String msg) {
        JSONObject json = new JSONObject();
        json.put("success", true);
        json.put("msg", msg);

        return json.toJSONString();
    }

    public static String success(String msg, Object rs) {
        JSONObject json = new JSONObject();
        json.put("success", true);
        json.put("msg", msg);
        json.put("rs", rs);

        return json.toJSONString();
    }

    public static String failure(String msg) {
        JSONObject json = new JSONObject();
        json.put("success", false);
        json.put("msg", msg);

        return json.toJSONString();
    }
}
