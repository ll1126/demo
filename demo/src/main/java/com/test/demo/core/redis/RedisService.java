package com.test.demo.core.redis;

import com.test.demo.core.redis.key.KeyPrefix;
import com.test.demo.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * redis 操作方法类
 */
@Service
public class RedisService {

    @Autowired
    JedisPool jedisPool;

    /**
     * redis get方法
     *
     * @param key
     * @param clazz 自定义类型  例如: int.class
     * @return
     */
    public <T> T get(KeyPrefix keyprefix, String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成真正的key
            String realKey = keyprefix.getPrefix() + key;
            String str = jedis.get(realKey);
            //将String 转化成 T 类型
            T t = StringUtil.StringToBean(str,clazz);
            return t;
        } finally {
            returnPool(jedis);
        }
    }

    /**
     * redis set方法
     *
     * @param key
     * @param value
     * @return
     */
    public <T> boolean set(KeyPrefix keyprefix, String key, T value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //将T 类型 转化成 String
            String str = StringUtil.beanToString(value);
            if (str == null) {
                return false;
            }
            //生成真正的key
            String realKey = keyprefix.getPrefix() + key;
            int seconds = keyprefix.expirSeconds();
            System.out.println("过期时间"+seconds);
            if (seconds <= 0) {
                // 永不过期
                jedis.set(realKey, str);
            } else {
                // 设置过期时间
                jedis.setex(realKey, seconds, str);
            }
            return true;
        } finally {
            returnPool(jedis);
        }
    }

    /**
     * 判断key是否存在
     *
     * @param key
     * @return
     */
    public <T> boolean exists(KeyPrefix keyprefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成真正的key
            String realKey = keyprefix.getPrefix() + key;
            return jedis.exists(realKey);
        } finally {
            returnPool(jedis);
        }
    }

    /**
     * 删除缓存
     *
     * @param key
     * @return
     */
    public <T> boolean delete(KeyPrefix keyprefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成真正的key
            String realKey = keyprefix.getPrefix() + key;
            long ret =  jedis.del(realKey);
            return ret > 0;
        } finally {
            returnPool(jedis);
        }
    }

    /**
     * 增加值 (原子操作)
     *
     * @param keyprefix
     * @param key
     * @return
     */
    public <T> Long incr(KeyPrefix keyprefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成真正的key
            String realKey = keyprefix.getPrefix() + key;
            return jedis.incr(realKey);
        } finally {
            returnPool(jedis);
        }
    }

    /**
     * 减少值 (原子操作)
     *
     * @param keyprefix
     * @param key
     * @return
     */
    public <T> Long decr(KeyPrefix keyprefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成真正的key
            String realKey = keyprefix.getPrefix() + key;
            return jedis.decr(realKey);
        } finally {
            returnPool(jedis);
        }
    }

    /**
     * 将Jedis连接放回连接池
     *
     * @param jedis
     */
    private void returnPool(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }

    }


}
