package com.hbh.httpclientdemo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**解析器*/
public class Parser {
	
	private Parser() {
		
	}
	
	public static void parse(String data) {
		Document dom = null;
		try {
			dom = Jsoup.parse(data);
		} catch (Exception e) {
			return;
		} 
		
		//解析地址
		Elements links = dom.getElementsByTag("a");
		for(Element link : links) {
			//获取a标签的link连接
			String str = link.attr("href");
			//添加到待爬取列
			String[] strs = str.split("/");
			if(strs.length > 2 && SystemConfig.JDLYHOST.equals(strs[2]) && !str.contains("jpg")) {
				HtmlManager.addCrowling(str);
			}
		}
		//下载图片
		Elements imgs = dom.getElementsByTag("img");
		for(Element img : imgs) {
			String str = img.attr("src");
			if(str != null && !"".equals(str) && !HtmlManager.containsImg(str)) {
				HtmlManager.addImgs(str);
				Downloader.download(str);
			}
		}
	}
}
