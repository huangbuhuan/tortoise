package jar;

import org.junit.Test;

import com.hbh.httpclientdemo.Downloader;
import com.hbh.redis.RedisClient;

import redis.clients.jedis.Jedis;

public class MyTest {
	
	@Test
	public void mkdirTest() {
		Downloader.mkdir("e://test");
	}
	
	@Test
	public void redisClientTest() {
		Jedis jedis = RedisClient.getResource();
		jedis.set("nosql", "redis");
		System.out.println(jedis.get("nosql"));
	}
}
