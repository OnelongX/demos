package com.ways2u.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * Created by huanglong on 16/9/8.
 */
public class RedisProvider {

    protected static final Logger LOG = Logger.getLogger(RedisProvider.class.getName());
    protected static JedisPool jedispool;
    protected static int EXPIRE = 130;

    static{
        ResourceBundle bundle = ResourceBundle.getBundle("redis");
        if (bundle == null) {
            throw new IllegalArgumentException(
                    "[redis.properties] is not found!");
        }

        EXPIRE = Integer.valueOf(bundle.getString("redis.expire"));

        JedisPoolConfig jredisConfig = new JedisPoolConfig();

        jredisConfig.setMaxWaitMillis(Long.valueOf(bundle.getString("redis.pool.maxWait")));
        /*
        jedisconfig.setMaxActive(Integer.valueOf(bundle
                .getString("redis.pool.maxActive")));
*/
        jredisConfig.setMaxIdle(Integer.valueOf(bundle
                .getString("redis.pool.maxIdle")));

        //jedisconfig.setMaxWait(Long.valueOf(bundle
         //       .getString("redis.pool.maxWait")));

        jredisConfig.setTestOnBorrow(Boolean.valueOf(bundle
                .getString("redis.pool.testOnBorrow")));
        jredisConfig.setTestOnReturn(Boolean.valueOf(bundle
                .getString("redis.pool.testOnReturn")));

        jedispool = new JedisPool(jredisConfig, bundle.getString("redis.ip"),
                Integer.valueOf(bundle.getString("redis.port")), 100000);
    }

    public static Jedis getJedis() {



        Jedis jedis = null;
        try {
            jedis = jedispool.getResource();
        } catch (Exception jce) {
            LOG.info(ExceptionUtil.getTrace(jce));
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                LOG.info(ExceptionUtil.getTrace(e));
            }
            jedis = jedispool.getResource();

        }
        return jedis;
    }




/*
    public static void returnResource(JedisPool pool, Jedis jedis) {
        try {
            jedis = pool.getResource();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

*/
}