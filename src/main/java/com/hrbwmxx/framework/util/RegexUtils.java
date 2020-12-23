package com.hrbwmxx.framework.util;

import java.util.regex.Pattern;
/**
 * 
* @title: RegexUtils 
* @description：常用类型校验
* @author： lijingyu
* @date： 2018年1月22日 上午9:55:59
 */
public class RegexUtils {
	public static boolean checkIdCard(String idCard) {
		 
		 String regex = "\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)"; 
		 return Pattern.matches("idCard", regex);
	}
	/**
	   * 验证手机号码（支持国际格式，+86135xxxx...（中国内地），+00852137xxxx...（中国香港））
	   * @param mobile 移动、联通、电信运营商的号码段
	   *<p>移动的号段：134(0-8)、135、136、137、138、139、147（预计用于TD上网卡）
	   *、150、151、152、157（TD专用）、158、159、187（未启用）、188（TD专用）</p>
	   *<p>联通的号段：130、131、132、155、156（世界风专用）、185（未启用）、186（3g）</p>
	   *<p>电信的号段：133、153、180（未启用）、189</p>
	   * @return 验证成功返回true，验证失败返回false
	   */
	public static boolean checkMobile(String mobile) { 
		String regex = "(\\+\\d+)?1[34578]\\d{9}$"; 
		return Pattern.matches(regex,mobile); 
	}
	/**
   * 验证整数（正整数和负整数）
   * @param digit 一位或多位0-9之间的整数
   * @return 验证成功返回true，验证失败返回false
   */ 
	public static boolean checkDigit(String digit) { 
		String regex = "\\-?[1-9]\\d+"; 
		return Pattern.matches(regex,digit); 
	}
	
	/**
	   * 验证整数和浮点数（正负整数和正负浮点数）
	   * @param decimals 一位或多位0-9之间的浮点数，如：1.23，233.30
	   * @return 验证成功返回true，验证失败返回false
	   */ 
	public static boolean checkDecimals(String decimals) { 
		String regex = "\\-?[1-9]\\d+(\\.\\d+)?"; 
		return Pattern.matches(regex,decimals); 
	} 

}
