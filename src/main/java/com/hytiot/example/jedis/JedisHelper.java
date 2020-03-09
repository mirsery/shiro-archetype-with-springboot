package com.hytiot.example.jedis;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @program: shiro-archetype-with-springboot
 * @description:
 * @author: misery
 * @create: 2020-03-09 20:11
 **/
@Service
public class JedisHelper {

    @Autowired
    private JedisPool jedisPool;

    private Jedis getRessource() {
        return jedisPool.getResource();
    }

    public byte[] set(byte[] key, byte[] value) {

        Jedis jedis = jedisPool.getResource();
        try {
            jedis.set(key, value);
            return value;
        } finally {
            jedis.close();
        }

    }

    /*设置指定key的超时时间，单位为s*/
    public void expire(byte[] key, int i) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.expire(key, i);
        } finally {
            jedis.close();
        }
    }

    public byte[] get(byte[] key) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.get(key);
        } finally {
            jedis.close();
        }
    }

    public void del(byte[] key) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.del(key);
        } finally {
            jedis.close();
        }
    }

    public Set<byte[]> getKeys(String shiro_session_prefix) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.keys((shiro_session_prefix + "*").getBytes());
        } finally {
            jedis.close();
        }
    }
}