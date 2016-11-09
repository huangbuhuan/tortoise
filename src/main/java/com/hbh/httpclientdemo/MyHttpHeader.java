package com.hbh.httpclientdemo;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicHeader;

/**
 * 配置请求头*/
public class MyHttpHeader {
	
	private static  List<BasicHeader> headers = new ArrayList<>();
	
	static{
		headers.add(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:49.0) Gecko/20100101 Firefox/49.0"));
		headers.add(new BasicHeader("Connection", "keep-alive"));
		headers.add(new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"));
		headers.add(new BasicHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3"));
	}
	
	public static BasicHeader[] getHeaders() {
		return (BasicHeader[])headers.toArray(new BasicHeader[headers.size()]);
	}
	
	public static void addHeader(BasicHeader header) {
		headers.add(header);
	}
}
