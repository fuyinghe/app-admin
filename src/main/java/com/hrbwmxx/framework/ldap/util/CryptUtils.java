package com.hrbwmxx.framework.ldap.util;

import java.io.IOException;
import java.io.PrintStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class CryptUtils
{
  private static final String DES = "DES";
  private static final String PASSWORD_CRYPT_KEY = "3L3A2dHHOrCiy7nL/sLe5rc6sKK2+8zhw9bLuTrHrLTvxsXN9Q==";
  private static final String[] hexDigits = { 
    "0", 
    "1", 
    "2", 
    "3", 
    "4", 
    "5", 
    "6", 
    "7", 
    "8", 
    "9", 
    "a", 
    "b", 
    "c", 
    "d", 
    "e", 
    "f" };

  public String convertToDigest(String input)
  {
    return byteArrayToHexString(md5(input)).toUpperCase();
  }

  private byte[] md5(String input)
  {
    MessageDigest alg = null;
    try {
      alg = MessageDigest.getInstance("MD5");
    }
    catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    alg.update(input.getBytes());
    byte[] digest = alg.digest();
    return digest;
  }

  public String byteArrayToHexString(byte[] b) {
    StringBuffer resultSb = new StringBuffer();
    for (int i = 0; i < b.length; i++) {
      resultSb.append(byteToHexString(b[i]));
    }
    return resultSb.toString();
  }

  private String byteToHexString(byte b) {
    int n = b;
    if (n < 0)
      n += 256;
    int d1 = n / 16;
    int d2 = n % 16;
    return hexDigits[d1] + hexDigits[d2];
  }

  public String base64Encode(String input) {
    byte[] byteInput = input.getBytes();
    BASE64Encoder encoder = new BASE64Encoder();
    return encoder.encode(byteInput);
  }

  public String base64Decode(String input) {
    BASE64Decoder decoder = new BASE64Decoder();
    byte[] outer = (byte[])null;
    try {
      outer = decoder.decodeBuffer(input);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return new String(outer);
  }

  private byte[] encrypt(byte[] src, byte[] key)
    throws Exception
  {
    SecureRandom sr = new SecureRandom();

    DESKeySpec dks = new DESKeySpec(key);

    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
    SecretKey securekey = keyFactory.generateSecret(dks);

    Cipher cipher = Cipher.getInstance("DES");

    cipher.init(1, securekey, sr);

    return cipher.doFinal(src);
  }

  private byte[] decrypt(byte[] src, byte[] key)
    throws Exception
  {
    SecureRandom sr = new SecureRandom();

    DESKeySpec dks = new DESKeySpec(key);

    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
    SecretKey securekey = keyFactory.generateSecret(dks);

    Cipher cipher = Cipher.getInstance("DES");

    cipher.init(2, securekey, sr);

    return cipher.doFinal(src);
  }

  public String encPassword(String pass)
  {
    String rtn = null;
    try {
      BASE64Encoder encoder = new BASE64Encoder();
      byte[] encValue = encrypt(pass.getBytes(), "3L3A2dHHOrCiy7nL/sLe5rc6sKK2+8zhw9bLuTrHrLTvxsXN9Q==".getBytes());
      rtn = encoder.encode(encValue);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return rtn;
  }

  public String decPassword(String secPass)
  {
    String rtn = null;
    try {
      BASE64Decoder decoder = new BASE64Decoder();
      byte[] decValue = decoder.decodeBuffer(secPass);
      rtn = new String(decrypt(decValue, "3L3A2dHHOrCiy7nL/sLe5rc6sKK2+8zhw9bLuTrHrLTvxsXN9Q==".getBytes()));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return rtn;
  }
  public static void main(String[] args) {
    System.out.println(new CryptUtils().encPassword("11111111"));
  }
}