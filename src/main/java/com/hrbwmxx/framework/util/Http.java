package com.hrbwmxx.framework.util;

import java.net.*;
import java.util.Map;
import java.util.Map.Entry;
import java.io.*;

/**
 * 
 * @author
 *
 */
public class Http {

	/**
	 * 
	 * @param method
	 * @param url
	 * @param data
	 * @return
	 */
	public static String Send(String method, String url, String data) {
		HttpURLConnection conn = null;
		InputStream in = null;
		InputStreamReader isr = null;
		OutputStream out = null;
		StringBuffer result = null;
		try {
			URL u = new URL(url);
			conn = (HttpURLConnection) u.openConnection();
			conn.setRequestProperty("Accept-Encoding", "gzip");
			conn.setRequestProperty("Connection", "keep-alive");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setConnectTimeout(60000);
			conn.setReadTimeout(60000);
			if (method.equals("POST")) {
				byte[] sendbyte = data.getBytes("UTF-8");
				out = conn.getOutputStream();
				out.write(sendbyte);
			}
			int status = conn.getResponseCode();
			if (status == 200) {
				String enc = conn.getContentEncoding();
				result = new StringBuffer();
				in = conn.getInputStream();
				enc = conn.getContentEncoding();
				if (enc != null && enc.equals("gzip")) {
					java.util.zip.GZIPInputStream gzin = new java.util.zip.GZIPInputStream(in);
					isr = new InputStreamReader(gzin, "UTF-8");
				} else {
					isr = new InputStreamReader(in, "UTF-8");
				}
				char[] c = new char[1024];
				int a = isr.read(c);
				while (a != -1) {
					result.append(new String(c, 0, a));
					a = isr.read(c);
				}
			} else {
				System.out.println("http code = " + status);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
			try {
				if (in != null) {
					in.close();
				}
				if (isr != null) {
					isr.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return result == null ? null : result + "";
	}

	/**
	 * 使用HttpURLConnection发送get请求
	 * 
	 * @param urlParam
	 *            url
	 * @param params
	 *            参数
	 * @param charset
	 *            字符
	 * @return String
	 */
	public static String sendGet(String urlParam, Map<String, Object> params, String charset) {
		StringBuffer resultBuffer = null;
		// 构建请求参数
		StringBuffer sbParams = new StringBuffer();
		if (params != null && params.size() > 0) {
			for (Entry<String, Object> entry : params.entrySet()) {
				sbParams.append(entry.getKey());
				sbParams.append("=");
				sbParams.append(entry.getValue());
				sbParams.append("&");
			}
		}
		HttpURLConnection con = null;
		BufferedReader br = null;
		try {
			URL url = null;
			if (sbParams != null && sbParams.length() > 0) {
				url = new URL(urlParam + "?" + sbParams.substring(0, sbParams.length() - 1));
			} else {
				url = new URL(urlParam);
			}
			con = (HttpURLConnection) url.openConnection();
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.connect();
			resultBuffer = new StringBuffer();
			br = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));
			String temp;
			while ((temp = br.readLine()) != null) {
				resultBuffer.append(temp);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					br = null;
					throw new RuntimeException(e);
				} finally {
					if (con != null) {
						con.disconnect();
						con = null;
					}
				}
			}
		}
		return resultBuffer.toString();
	}

}
