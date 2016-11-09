package com.hbh.httpclientdemo;

import java.io.Closeable;
import java.io.IOException;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**连接管理*/
public class HttpClientManager {
	
	private static PoolingHttpClientConnectionManager httpClientPool = null;
	
	private HttpClientManager() {
		
	}
	
	static {
		httpClientPool = new PoolingHttpClientConnectionManager();
		//设置最大连接数
		httpClientPool.setMaxTotal(200);
		//设置最大路由连接数
		httpClientPool.setDefaultMaxPerRoute(50);
	}
	
	/**从连接池中获取资源*/
	public static CloseableHttpClient getClient() {
		return HttpClients.custom().setConnectionManager(httpClientPool).build();
	}
	
	/**返回连接给连接池*/
	public static void close(Closeable request) {
		if(request != null) {
			try {
				request.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
