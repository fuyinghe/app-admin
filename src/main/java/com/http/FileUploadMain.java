package com.http;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.commons.io.FileUtils;

import com.SHA256withRSA.SHA256withRSA;
import com.alibaba.fastjson.JSONObject;

public class FileUploadMain {

	public static String prikey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCDcj2lNGdpEwFY196A/pcT9s9/+XgfI6rYb5GeBoOattnEL7JAe3Zbob0XtZDOnuYP+3yaorqVmDvwFAsACt9ac8jXXpghvLHIW8rdv25jbjlajOcwQy/H42+tBKgZV7oO3r8zj58PvjXjj7wwFEHiacRMI3Y6YCYi60omKQeCk9bY7L5FK7Mym4GrNT4+tv5A/zyBS7NuaCO8reup5BVtg2MjVuizoMDOPNr7sT1oqFj21KqbIrL+TzQe/hiddOrRVY8llAtXmq0lvMcjfnfgCtbs+rhYijIXjfi1VEh+cq9Gl4RY/3Vh5KE58jgVk7ggFuyvDmr57sDtb62V8RgZAgMBAAECggEAKTNfuy6n0zmPzIctSQWD2VAdTiGl0yzc2iZuYDfufEogC+xfVPLd8G7L1Gim0PzhY9USA25KSaWD9AZ0RDkkNTfhlhAiY0DAAOs0tTjRovzjoUxVEr2cdm0FjhfNkNP8j7il+cVkuAyI26Er/W38ELnSO4NKBZYpnfxeK4PkxLqZW8/NKUXJni7FaPni2S7tWhgkt7Zqa/gdLnCQMLaf9xePhLzqrM2gT10kkZjBDQ4WqvfsgLRMUJ8B40e3QezYxTtYlzaZCSibBMI+gMnBKJOzXf01wUybNpBWYmDFMDoMColILB2GeSTzO92V0DceewaOamBpKaXTj/DeMFZuuQKBgQDbiHF+/GOIirzdWe0SiJkKmv4MRvIcTGrwGOepdEimbDvFaq+M4JxS0n5drufxq1E8kmDAcLfatvf7NZ0+FZBMMMfl3giLcpZvgCxKxABefdZdkOsTvTgG0ckKwf9qO2Yq+A6tlk52Hv80tecXsIDGdvQnU/52xryTUBcbdWjfCwKBgQCZR/FaPUFDjkRwU/uHq21azGUNCUjKH6O1ULRBWJdQBv5OLvCxqvwXSJGAYaG1JdwP15jI4jNzJfmmM8IT3Jw65zAlhXe01l2LEf7PohAVN5LDqd5Lq2rwCYhAV5aspk7Wc2gHhTsyRgTZWeLHF7i1Xc0JlmW5mgW4/t7QExCr6wKBgCs+z4zCTyEgo1+/TTIvcmZibdUhTKRCcXZmkYwR+hW+kG+tOnO381NlX7s4rzwuEUyrUR/XlIAjNupnf1gxi0FXAqnHeUtvAS9pwk/gGGqEw2ufFo/G4HiHbuENojDdDp08TDfpuf8O0BskEifafyOZXzM4GpJvR8qFJmgkUspNAoGAaWGl1FWixhBMizGiD59TOoalvrWwXo4sHh8THo4K0ZFNS3FIN84HLPbOWgZFh+Y0iou+VfX2S2dDYPnap48XtgN1/YXqS+DJRTClEBkql3uyomTqGPoMNmVHUH0ncSGRuCx1zB3UGfc7pDcBC8IKUl7f9YR6AYWcA5julP1Wi+sCgYEAyysEFyNAlTaDrtVMqYcRZ2Gufqoi/2qUzFFQnT6HTuhiB+S67p0VYaTPI4/XllrftkfiVYmt/gI2YuGhzrgO3vF6ucsqrNfnFdeA2HUomcO4XkFygt/die1pgAZ+XRL232F1kIWSbmIoerVBubc+vAZpEtVjVVJNgMMPSz29Nrs=";

	public static String send() {
		// 编辑请求参数
		String vNo = "2";
		String sgnAlgr = "SHA256withRSA";
		// String txnDt="20181203101010";
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
		String txnDt = sd.format(new Date());
		String btNo = txnDt;
		String fileNm = "data.zip";
		String onLnOflnIndCd = "1";
		String filePaht = "D:/work/data.zip";
		String brInsNo = "230000000";
//		计算文件的MD5值
		String md5 = getMD5One(filePaht);
//		获取签名信息
		String sing_byte = SHA256withRSA.sign(prikey, toString(vNo, sgnAlgr, txnDt, btNo, fileNm, md5,brInsNo, onLnOflnIndCd));
//		 
		JSONObject json = new JSONObject();
		json.put("vNo", vNo);
		json.put("sgnAlgr", sgnAlgr);
		json.put("txnDt", txnDt);
		json.put("btNo", btNo);
		json.put("fileNm", fileNm);
		json.put("md5", md5);
		json.put("brInsNo", brInsNo);
		json.put("singInf", sing_byte);
		json.put("onLnOflnIndCd", onLnOflnIndCd);
		
		System.out.println("签名=" + sing_byte);
		// 调发送方法发送文件 两个参数 参数一:文件的绝对路径  类型String ， 参数二：请求参数 类型：JSON字符串
		String result = upload(filePaht, json.toString());
		return result;
	}
	// 发送请求的方法
	public static String upload(String localFile, String json) {
		
		System.out.println("==========开始========" + localFile);
		long time = new Date().getTime();
		System.out.println(time);
		File file = new File(localFile);
		// Protocol myhttps = new Protocol("https", new MySSLSocketFactory(), 443);
		// Protocol.registerProtocol("https", myhttps);
		
		//PostMethod filePost = new PostMethod("http://govpaytesthljnh.mytunnel.site/online/customerInfoSynch");
		
		PostMethod filePost = new PostMethod("https://hljnh.govpay.ccb.com/online/customerInfoSynch");
		
		//PostMethod filePost = new PostMethod("https://hljjmsswj.govpay.ccb.com/online/customerInfoSynch");
		HttpClient client = new HttpClient();
		String response = new String();
		try {
			Part[] parts = { new CustomFilePart("file", file), new StringPart("jsonStr", json) };
			filePost.setRequestEntity(new MultipartRequestEntity(parts, filePost.getParams()));
			client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
			int status = client.executeMethod(filePost);
			System.out.println(status);
			if (status == HttpStatus.SC_OK) {
				//System.out.println("成功");
			} else {
				//System.out.println("失败");
			}

			response = filePost.getResponseBodyAsString().trim();
			System.out.println("返回回来的数据：" + response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			filePost.releaseConnection();
		}
		long time2 = new Date().getTime();
		//System.out.println(time2);
		//System.out.println(time2 - time);
		return response;
	}

	private final static String[] strHex = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e",
			"f" };

	// 计算文件的MD5
	private static String getMD5One(String path) {
		StringBuffer sb = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] b = md.digest(FileUtils.readFileToByteArray(new File(path)));
			for (int i = 0; i < b.length; i++) {
				int d = b[i];
				if (d < 0) {
					d += 256;
				}
				int d1 = d / 16;
				int d2 = d % 16;
				sb.append(strHex[d1] + strHex[d2]);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	//获取签名信息的 原串
	public static String toString(String vNo, String sgnAlgr, String txnDt, String btNo, String fileNm, String md5, String brInsNo,String onLnOflnIndCd) {

		return "vNo=" + vNo + "&sgnAlgr=" + sgnAlgr + "&txnDt=" + txnDt + "&btNo=" + btNo + "&fileNm=" + fileNm
				+ "&md5=" + md5+"&brInsNo="+brInsNo;
	}

}
