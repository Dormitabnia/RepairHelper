package com.ss.rh.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class RedisCacheUtil {
    private static final Logger logger = LoggerFactory.getLogger(RedisCacheUtil.class);

    @Autowired
    private JedisPool jedisPool;

    public String get(String key) {
        Jedis jedis = null;

        jedis = jedisPool.getResource();

        return null;
    }
}
