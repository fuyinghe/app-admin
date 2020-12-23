package com.SHA256withRSA;

import java.util.Map;

public class TestSha {

	public static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
	public static final String ENCODE_ALGORITHM = "SHA-256";
	public static final String PLAIN_TEXT = "vNo=2&sgnAlgr=SHA256withRSA&txnDt=20181010101010&btNo=1&fileNm=data.zip&md5=6be3e45f9afca6605801ee7b45cbc8d4";
	public static final String PLAIN_TEXTq = "vNo=2&sgnAlgr=SHA256withRSA&txnDt=20181010101010&btNo=1&fileNm=data.zip&md5=3fd0206e2ea490c2ef37cc15e98a80be";
	
	public static String pubkey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAg3I9pTRnaRMBWNfegP6XE/bPf/l4HyOq2G+RngaDmrbZxC+yQHt2W6G9F7WQzp7mD/t8mqK6lZg78BQLAArfWnPI116YIbyxyFvK3b9uY245WoznMEMvx+NvrQSoGVe6Dt6/M4+fD74144+8MBRB4mnETCN2OmAmIutKJikHgpPW2Oy+RSuzMpuBqzU+Prb+QP88gUuzbmgjvK3rqeQVbYNjI1bos6DAzjza+7E9aKhY9tSqmyKy/k80Hv4YnXTq0VWPJZQLV5qtJbzHI3534ArW7Pq4WIoyF434tVRIfnKvRpeEWP91YeShOfI4FZO4IBbsrw5q+e7A7W+tlfEYGQIDAQAB";
	public static String prikey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCDcj2lNGdpEwFY196A/pcT9s9/+XgfI6rYb5GeBoOattnEL7JAe3Zbob0XtZDOnuYP+3yaorqVmDvwFAsACt9ac8jXXpghvLHIW8rdv25jbjlajOcwQy/H42+tBKgZV7oO3r8zj58PvjXjj7wwFEHiacRMI3Y6YCYi60omKQeCk9bY7L5FK7Mym4GrNT4+tv5A/zyBS7NuaCO8reup5BVtg2MjVuizoMDOPNr7sT1oqFj21KqbIrL+TzQe/hiddOrRVY8llAtXmq0lvMcjfnfgCtbs+rhYijIXjfi1VEh+cq9Gl4RY/3Vh5KE58jgVk7ggFuyvDmr57sDtb62V8RgZAgMBAAECggEAKTNfuy6n0zmPzIctSQWD2VAdTiGl0yzc2iZuYDfufEogC+xfVPLd8G7L1Gim0PzhY9USA25KSaWD9AZ0RDkkNTfhlhAiY0DAAOs0tTjRovzjoUxVEr2cdm0FjhfNkNP8j7il+cVkuAyI26Er/W38ELnSO4NKBZYpnfxeK4PkxLqZW8/NKUXJni7FaPni2S7tWhgkt7Zqa/gdLnCQMLaf9xePhLzqrM2gT10kkZjBDQ4WqvfsgLRMUJ8B40e3QezYxTtYlzaZCSibBMI+gMnBKJOzXf01wUybNpBWYmDFMDoMColILB2GeSTzO92V0DceewaOamBpKaXTj/DeMFZuuQKBgQDbiHF+/GOIirzdWe0SiJkKmv4MRvIcTGrwGOepdEimbDvFaq+M4JxS0n5drufxq1E8kmDAcLfatvf7NZ0+FZBMMMfl3giLcpZvgCxKxABefdZdkOsTvTgG0ckKwf9qO2Yq+A6tlk52Hv80tecXsIDGdvQnU/52xryTUBcbdWjfCwKBgQCZR/FaPUFDjkRwU/uHq21azGUNCUjKH6O1ULRBWJdQBv5OLvCxqvwXSJGAYaG1JdwP15jI4jNzJfmmM8IT3Jw65zAlhXe01l2LEf7PohAVN5LDqd5Lq2rwCYhAV5aspk7Wc2gHhTsyRgTZWeLHF7i1Xc0JlmW5mgW4/t7QExCr6wKBgCs+z4zCTyEgo1+/TTIvcmZibdUhTKRCcXZmkYwR+hW+kG+tOnO381NlX7s4rzwuEUyrUR/XlIAjNupnf1gxi0FXAqnHeUtvAS9pwk/gGGqEw2ufFo/G4HiHbuENojDdDp08TDfpuf8O0BskEifafyOZXzM4GpJvR8qFJmgkUspNAoGAaWGl1FWixhBMizGiD59TOoalvrWwXo4sHh8THo4K0ZFNS3FIN84HLPbOWgZFh+Y0iou+VfX2S2dDYPnap48XtgN1/YXqS+DJRTClEBkql3uyomTqGPoMNmVHUH0ncSGRuCx1zB3UGfc7pDcBC8IKUl7f9YR6AYWcA5julP1Wi+sCgYEAyysEFyNAlTaDrtVMqYcRZ2Gufqoi/2qUzFFQnT6HTuhiB+S67p0VYaTPI4/XllrftkfiVYmt/gI2YuGhzrgO3vF6ucsqrNfnFdeA2HUomcO4XkFygt/die1pgAZ+XRL232F1kIWSbmIoerVBubc+vAZpEtVjVVJNgMMPSz29Nrs=";
	public static String sing_byte = "";
	public static void main(String[] args) {
		System.out.println(PLAIN_TEXT.equals(PLAIN_TEXTq));
		// 公私钥对
//		generateKeyBytes();
		// 签名
	    sign();
		// 验签	
		verifySign();
	}
	
	// 生成公私钥对
	public static void generateKeyBytes() {
		// 公私钥对
		Map<String, String> keyMap = SHA256withRSA.generateKeyBytes();
		pubkey =  keyMap.get(SHA256withRSA.PUBLIC_KEY) ;
		prikey =  keyMap.get(SHA256withRSA.PRIVATE_KEY) ;
		System.out.println("公钥 = " + pubkey);
		System.out.println("私钥 = " + prikey);
	}
	 
	// 签名
	public static void sign() {
		// 签名
		sing_byte = SHA256withRSA.sign(prikey, PLAIN_TEXT);
		System.out.println("签名 = " + sing_byte);
	}
	// 签名
	public static void verifySign() {
		// 验签
		boolean flag = SHA256withRSA.verifySign(pubkey, PLAIN_TEXT, sing_byte);
		System.out.println(flag);
				
	}
	
	
	

}
