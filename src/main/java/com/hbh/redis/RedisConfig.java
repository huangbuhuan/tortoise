package com.hbh.redis;

public class RedisConfig {
	
	public static final String HOST="192.168.2.191";
	
	public static final int PORT=6379;
	//最大连接实例数
	public static final int MAX_ACTIVE=1024;
	//一个连接池空闲jedis实例数
	public static final int MAX_IDLE=200;
	//等待时间
	public static final int MAX_WAIT=10000;
	//登录时间
	public static final int TIMEOUT=10000;
	//密码
	//public static final String AUTH="123456";
	//
	public static final Boolean TEST_ON_BORROW=true;
}
