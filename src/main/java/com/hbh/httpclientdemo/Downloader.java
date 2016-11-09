package com.hbh.httpclientdemo;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**下载器*/
public class Downloader {
	
	public static void download(String url) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		try {
			HttpGet httpGet = new HttpGet(url);
			httpGet.setHeaders(MyHttpHeader.getHeaders());
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			long length = entity.getContentLength();
			//System.out.println(EntityUtils.toString(entity));会出错
			if(length < 60000) {
				//System.out.println("文件过小");
				return ;
			} else {
				InputStream is = entity.getContent();
				OutputStream os = new FileOutputStream(new File(SystemConfig.JDLYPATH + System.currentTimeMillis() + SystemConfig.JPG));
				download(is, os);
				//System.out.println("---结束----");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			HttpClientManager.close(httpClient);
		}
	}
	
	public static void download(InputStream is, OutputStream os) {
		byte[] data = new byte[1024];
		int index = 0;
		try {
			while((index = is.read(data)) != -1) {
				os.write(data, 0, index);
			}
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(is);
			close(os);
		}
	}
	
	/**关闭流*/
	private static void close(Closeable file) {
		if(file != null) {
			try {
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**根据标签创建文件夹*/
	public static void mkdir(String fileName) {
		File file = new File(fileName);
		if(!file.exists()) {
			file.mkdir();
		}
	}
}
