package com.ss.rh.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class RedisCacheUtil {

    @Autowired
    private JedisPool jedisPool;

    /*
    返回的是json字符串
     */
    public String get(String key) {
        Jedis jedis = jedisPool.getResource();

        return jedis.get(key);
    }

    /*
    将对象转化为json字符串存储
     */
    public void set(String key, Object value) {
        Jedis jedis = jedisPool.getResource();

        jedis.set(key, JsonUtil.object2JsonStr(value));
    }

    /*
    判断是否存在
     */
    public boolean existsKey(String key) {
        Jedis jedis = jedisPool.getResource();

        return jedis.exists(key);
    }
}
