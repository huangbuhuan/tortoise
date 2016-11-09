package com.hbh.httpclientdemo;

import com.hbh.redis.RedisClient;
import com.hbh.redis.RedisDictionary;

import redis.clients.jedis.Jedis;

/**
 * html管理器*/

public class HtmlManager {
	
	static final String HTMLKEY = RedisDictionary.JDLYHMTLS;
	static final String IMGKEY = RedisDictionary.JDLYIMGS;
	static final String QUEUEKEY = RedisDictionary.JDLYQUEUE;
	
	public static void addCrowled(String html) {
		Jedis jedis = RedisClient.getResource();
		try {
			if(html != null && html != "") {
				jedis.sadd(HTMLKEY, html);
			}
		} catch (Exception e) {
			throw new RuntimeException();
		} finally {
			RedisClient.returnResource(jedis);
		}
	}
	
	/**进队列*/
	public static void addCrowling(String html) {
		Jedis jedis = RedisClient.getResource();
		try {
			if(!jedis.sismember(HTMLKEY, html)) {
				jedis.rpush(QUEUEKEY, html);
				addCrowled(html);
			}
		} catch (Exception e) {
			throw new RuntimeException();
		} finally {
			RedisClient.returnResource(jedis);
		}
	}
	
	/**出队列*/
	public static String remove() {
		Jedis jedis = RedisClient.getResource();
		try{
			return jedis.lpop(QUEUEKEY);
		} catch(Exception e){
			throw new RuntimeException();
		} finally{
			RedisClient.returnResource(jedis);
		}		
	}
	
	/**获取队列长度*/
	public static long getLength() {
		Jedis jedis = RedisClient.getResource();
		try {
			return jedis.llen(QUEUEKEY);
		} catch (Exception e) {
			throw new RuntimeException();
		} finally {
			RedisClient.returnResource(jedis);
		}
	}
	
	public static void addImgs(String url) {
		Jedis jedis = RedisClient.getResource();
		try {
			jedis.sadd(IMGKEY, url);

		} catch (Exception e) {
			throw new RuntimeException();
		} finally {
			RedisClient.returnResource(jedis);
		}
	}
	
	public static boolean containsImg(String url) {
		Jedis jedis = RedisClient.getResource();
		try {
			return jedis.sismember(IMGKEY, url);
		} catch (Exception e) {
			throw new RuntimeException();
		} finally {
			RedisClient.returnResource(jedis);
		}
	}
	
}
