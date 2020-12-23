package com.SHA256withRSA;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

public class SHA256withRSA {

	public static final String KEY_ALGORITHM = "RSA";
	public static final String CIPHER_ALGORITHM = "RSA/ECB/PKCS1Padding";
	public static final String PUBLIC_KEY = "publicKey";
	public static final String PRIVATE_KEY = "privateKey";
	public static final int KEY_SIZE = 2048;
	public static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
	public static final String ENCODE_ALGORITHM = "SHA-256";
	public static final String PLAIN_TEXT = "test string test stringtest stringtest stringtest stringtest stringtest stringtest string";

	/**
	 * 鐢熸垚瀵嗛挜瀵�
	 * 
	 * @return
	 */
	public static Map<String, String> generateKeyBytes() {
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator
					.getInstance(KEY_ALGORITHM);
			keyPairGenerator.initialize(KEY_SIZE);
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
			RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
			Map<String, String> keyMap = new HashMap();
			keyMap.put(PUBLIC_KEY, Base64.encodeBase64String(publicKey
					.getEncoded()));
			keyMap.put(PRIVATE_KEY, Base64.encodeBase64String(privateKey
					.getEncoded()));
			return keyMap;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.out.println("绛惧悕楠岃瘉澶辫触");

		}
		return null;
	}

	/**
	 * 杩樺師鍏挜
	 * 
	 * @param keyBytes
	 * @return
	 */
	public static PublicKey restorePublicKey(String publicKey) {
		byte[] prikeyByte;
		PublicKey pubTypeKey = null;
		try {
			prikeyByte = Base64.decodeBase64(publicKey.getBytes("UTF-8"));
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(
					prikeyByte);
			KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);

			pubTypeKey = factory.generatePublic(x509EncodedKeySpec);
			return pubTypeKey;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();

		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return null;
	}

	/**
	 * 杩樺師绉侀挜
	 * 
	 * @param keyBytes
	 * @return
	 */
	public static PrivateKey restorePrivateKey(String privateKey) {
		byte[] prikeyByte;
		PrivateKey priTypeKey = null;
		try {
			prikeyByte = Base64.decodeBase64(privateKey.getBytes("UTF-8"));
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(
					prikeyByte);
			KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
			priTypeKey = factory.generatePrivate(pkcs8EncodedKeySpec);
			return priTypeKey;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();

		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return null;
	}

	/**
	 * 绛惧悕
	 * 
	 * @param privateKey
	 *            绉侀挜
	 * @param plain_text
	 *            鏄庢枃
	 * @return
	 */
	public static String sign(String privateKey, String plain_text) {
		return Base64.encodeBase64String(sign(restorePrivateKey(privateKey),
				plain_text));
	}

	/**
	 * 绛惧悕
	 * 
	 * @param privateKey
	 *            绉侀挜
	 * @param plain_text
	 *            鏄庢枃
	 * @return
	 */
	public static byte[] sign(PrivateKey privateKey, String plain_text) {
// MessageDigest messageDigest;
		byte[] signed = null;
		try {
// messageDigest = MessageDigest.getInstance(ENCODE_ALGORITHM);
// messageDigest.update(plain_text.getBytes());
// byte[] outputDigest_sign = messageDigest.digest();
// System.out.println("SHA-256鍔犲瘑鍚�----銆� +
// bytesToHexString(outputDigest_sign));
			Signature Sign = Signature.getInstance(SIGNATURE_ALGORITHM);
			Sign.initSign(privateKey);
			Sign.update(plain_text.getBytes());
			signed = Sign.sign();
// System.out.println("SHA256withRSA绛惧悕鍚�----銆� + bytesToHexString(signed));
		} catch (NoSuchAlgorithmException |	InvalidKeyException | SignatureException e) {
			e.printStackTrace();
		}
		return signed;
	}

	/**
	 * 楠岀
	 * 
	 * @param publicKey
	 *            鍏挜
	 * @param plain_text
	 *            鏄庢枃
	 * @param signed
	 *            绛惧悕
	 */
	public static boolean verifySign(String publicKey, String srcMsg,
			String signed) {
		PublicKey publicTypeKey = restorePublicKey(publicKey);
		return verifySign(publicTypeKey, srcMsg, signed);

	}

	/**
	 * 楠岀
	 * 
	 * @param publicKey
	 *            鍏挜
	 * @param plain_text
	 *            鏄庢枃
	 * @param signed
	 *            绛惧悕
	 */
	private static boolean verifySign(PublicKey publicKey, String plain_text, String signed) {
// MessageDigest messageDigest;
		boolean SignedSuccess = false;
		try {
// messageDigest = MessageDigest.getInstance(ENCODE_ALGORITHM);
// messageDigest.update(plain_text.getBytes("UTF-8"));
// byte[] outputDigest_verify = messageDigest.digest();
			// System.out.println("SHA-256鍔犲瘑鍚�----銆�
			// +bytesToHexString(outputDigest_verify));
			Signature verifySign = Signature.getInstance(SIGNATURE_ALGORITHM);
			verifySign.initVerify(publicKey);
			verifySign.update(plain_text.getBytes("UTF-8"));
			SignedSuccess = verifySign.verify(Base64.decodeBase64(signed.getBytes("UTF-8")));
		} catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SignedSuccess;
	}

	/**
	 * bytes[]鎹㈡垚16杩涘埗瀛楃涓�
	 * 
	 * @param src
	 * @return
	 */
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	/**
	 * 鍗佸叚杩涘埗鐨勫瓧绗︿覆杞崲涓哄瓧鑺傛暟缁�
	 * 
	 * @param src
	 * @return
	 */
	public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];

		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (chartobyte(hexChars[pos]));
		}
		return d;
	}

	private static byte chartobyte(char c) {
		// TODO Auto-generated method stub
		return (byte) "123456789abcdef".indexOf(c);
	}
}
