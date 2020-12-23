package com.hrbwmxx.framework.util;

import java.util.ArrayList;
import java.util.List;

public class StringSplit {
	public static List<String> getListFromContent(String content, int count) {
		 
		List<String> list = new ArrayList<String>();
		// 获取String的总长度
		int contentLength = content.length();
 
		if (contentLength < count) {
			list.add(content);
		} else {
 
			int begin = 0;
			// 获取需要切割多少段
			int cutCount = contentLength / count;
 
			int cutCounts = contentLength % count;
 
			// 获取切割段的长度
			if (cutCounts != 0) {
				cutCount++;
			}
 
			for (int i = 1; i <= cutCount; i++) {
				String temp;
				// 不是最后一段
				if (i != cutCount) {
 
					temp = content.substring(begin, count * i);
 
				} else {
					temp = content.substring(begin, contentLength);
				}
 
				begin = count * i;
				list.add(temp);
			}
 
		}
 
		return list;
	}
    public static void main(String[] args) {
		String s="ffkkhhggeeddyybbmm";
		List<String> list=getListFromContent(s,2);
		for (String string : list) {
			System.out.println(string);
		}
	}
}
