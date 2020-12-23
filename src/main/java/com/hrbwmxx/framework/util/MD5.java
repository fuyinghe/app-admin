/**
 * @author MaRui
 * @E-mail:marui-2007@163.com
 * @qq:552013500
 * @version ����ʱ�䣺2011-1-17 ����07:16:01
 * ��˵��:������ܳ���
 */
package com.hrbwmxx.framework.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	/**
	 * MD5
	 */
    public static String toMD5(String input) 
    {
        MessageDigest alg;
        byte[] digest = null;
		try {
			alg = MessageDigest.getInstance("MD5");
			alg.update(input.getBytes());
	        digest = alg.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
        return byte2hex(digest);
    }
    
    public static String byte2hex(byte[] b) 
    {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else hs = hs + stmp;
        } 
        return hs.toLowerCase();
    }
}
