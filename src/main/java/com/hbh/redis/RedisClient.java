package com.hbh.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisClient {
	
	private RedisClient() {
		
	}
	private static JedisPool jedisPool;
	
	static {
		GenericObjectPoolConfig jedisPoolConfig=new GenericObjectPoolConfig();
		jedisPoolConfig.setMaxTotal(RedisConfig.MAX_ACTIVE);
		jedisPoolConfig.setMaxWaitMillis(RedisConfig.MAX_WAIT);
		jedisPoolConfig.setMaxIdle(RedisConfig.MAX_IDLE);
		jedisPoolConfig.setTestOnBorrow(RedisConfig.TEST_ON_BORROW);
		jedisPool=new JedisPool(jedisPoolConfig,RedisConfig.HOST,RedisConfig.PORT,RedisConfig.TIMEOUT);
	}
	
	public static Jedis getResource(){
		return jedisPool.getResource();
	}
	
	public static void returnResource(Jedis jedis){
		jedis.close();
	}
}
