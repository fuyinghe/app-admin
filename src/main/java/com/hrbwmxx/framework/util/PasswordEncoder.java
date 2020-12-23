package com.hrbwmxx.framework.util;

import java.math.BigDecimal;
import java.text.MessageFormat;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;


public class PasswordEncoder {
	public static final String FORMAT_PWD_SALT = "[!@{0}#$]";

	public static String cryptoPassword(BigDecimal userId, String password) {
		String salt = MessageFormat.format(FORMAT_PWD_SALT, userId);
		return new Md5PasswordEncoder().encodePassword(password, salt);
	}

//	public static void main(String[] args) {
//		BigDecimal userID = new BigDecimal(2);
//		String password = "admin";
//		String cryptedPassword = PasswordEncoder.cryptoPassword(userID, password);
//		System.out.println(cryptedPassword);
//	}
}