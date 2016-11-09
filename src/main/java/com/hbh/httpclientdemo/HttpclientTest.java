package com.hbh.httpclientdemo;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**注程序*/
public class HttpclientTest {
	
	/**待优化*/
	public static void get(String url) {
		if(url.contains("jpg")) {
			return;
		}
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			//创建连接
			HttpGet httpGet = new HttpGet(url);
			httpGet.setHeaders(MyHttpHeader.getHeaders());
			//执行get请求
			CloseableHttpResponse response = httpClient.execute(httpGet);
			//获取状态码
			//System.out.println(response.getStatusLine());
			//获取响应体
			HttpEntity entity = response.getEntity();
			if(entity != null) {
				Parser.parse(EntityUtils.toString(entity, "utf-8"));
				//System.out.println(EntityUtils.toString(entity, "utf-8"));
				//响应长度
				//System.out.println(entity.getContentLength());
				//System.out.println(entity.getContentEncoding());
				//获取下载内容
				//System.out.println(entity.getContent());
				//获取返回类型
				//System.out.println(entity.getContentType());
				//关闭输入流
				EntityUtils.consume(entity);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			HttpClientManager.close(httpClient);
		}
	}
	
	public void post(String url) {
		CloseableHttpClient htppClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		try {
			htppClient.execute(post);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		get("http://www.jdlingyu.moe");
		ExecutorService exec = Executors.newCachedThreadPool();
		int i = 0;
		while(i++ < 5) {
			exec.execute(new Runnable() {
				
				@Override
				public void run() {
					while(true) {
						while(HtmlManager.getLength() > 0) {
							get(HtmlManager.remove());
							try {
								TimeUnit.MILLISECONDS.sleep(1);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
					
				}
			});
		}
		exec.shutdown();
	}
}
