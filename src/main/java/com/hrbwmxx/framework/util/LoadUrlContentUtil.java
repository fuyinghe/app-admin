package com.hrbwmxx.framework.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoadUrlContentUtil {
	
	/**
	 * 获取网页内容
	 * @param url
	 * @return
	 */
	public static String getUrlContent(String url) {
		String content = Http.Send("GET", url, "");
		//String content = GetURLHTML.getHtml(url);
		content=content.replaceAll("\r|\n", "");
		return content;
	}
	
	/**
	 * 通过正则表达式进行数据处理抽取
	 * @param url
	 * @param zzbds
	 * @return
	 */
	public static String getjaxContent(String content,String zzbds) {
		Pattern pattern = Pattern.compile(zzbds);
        Matcher matcher = pattern.matcher(content);
        String backstr = "";
        while (matcher.find()) {
        	backstr = backstr+matcher.group();
        }
		
		return backstr;
	}
	
	public static void main(String[] args) {
		
    }
	
	
}