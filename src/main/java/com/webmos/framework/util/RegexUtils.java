package com.webmos.framework.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.regex.Matcher;
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
	
	/**
	 * 判断输入值是否是整数
	 * @param str
	 * @return
	 */
	public static boolean isInt(String str){
		try{
			Integer.parseInt(str);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public static int toInt(String str){
		try{
			return Integer.parseInt(str);
		}catch(Exception e){
			return 0;
		}
	}
	/**
	 * 判断是否是数字
	 * @param str
	 * @return
	 */
	public static boolean isFloat(String str){
		try{
			Float.parseFloat(str);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	public static Float toFloat(String str){
		try{
			return Float.parseFloat(str);
		}catch(Exception e){
			return (float) 0;
		}
	}
	/**
	 * 判断是否是Email
	 * @param str
	 * @return
	 */
	public static boolean isEmail(String str){
		 Pattern p = Pattern.compile("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+");  
	     Matcher m = p.matcher(str);
	     boolean b = m.matches();
	     return b;
	}
	
	/**
	 * 判断是否是日期类型
	 * 例如  2017-12-12
	 * @param str
	 * @return
	 */
	public static boolean isDate(String str){
		 Pattern p = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}");  
	     Matcher m = p.matcher(str);
	     boolean b = m.matches();
	     return b;
	}
	/**
	 * 判断是否是日期类型
	 * 例如  12:12:12
	 * @param str
	 * @return
	 */
	public static boolean isTime(String str){
		 Pattern p = Pattern.compile("[0-9]{2}:[0-9]{2}:[0-9]{2}");  
	     Matcher m = p.matcher(str);
	     boolean b = m.matches();
	     return b;
	}
	/**
	 * 判断是否是日期类型
	 * 例如  2017-12-12 12:12:12
	 * @param str
	 * @return
	 */
	public static boolean isDateTime(String str){
		 Pattern p = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}");  
	     Matcher m = p.matcher(str);
	     boolean b = m.matches();
	     return b;
	}
	
	public static boolean IDCardValidate(String IDStr) {        
        String tipInfo = "该身份证有效！";
        boolean ispassed = true;
        String Ai = "";  
        // 判断号码的长度 15位或18位  
        if (IDStr.length() != 15 && IDStr.length() != 18) {  
            System.out.println("身份证号码长度应该为15位或18位");
            return false;
        }  
           
   
        // 18位身份证前17位位数字，如果是15位的身份证则所有号码都为数字  
        if (IDStr.length() == 18) {  
            Ai = IDStr.substring(0, 17);  
        } else if (IDStr.length() == 15) {  
            Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);  
        }  
        if (isNumeric(Ai) == false) {  
            System.out.println("身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字");
            return false;  
        }  

        // 判断出生年月是否有效   
        String strYear = Ai.substring(6, 10);// 年份  
        String strMonth = Ai.substring(10, 12);// 月份  
        String strDay = Ai.substring(12, 14);// 日期  
        if (isDateA(strYear + "-" + strMonth + "-" + strDay) == false) {  
            System.out.println("身份证出生日期无效");
            return false;
        }  
        GregorianCalendar gc = new GregorianCalendar();  
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");  
        try {  
            if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150  
                    || (gc.getTime().getTime() - s.parse(  
                            strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {  
                System.out.println("身份证生日不在有效范围");
                return false;
            }  
        } catch (NumberFormatException e) {  
            e.printStackTrace();  
        } catch (java.text.ParseException e) {  
            e.printStackTrace();  
        }  
        if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {  
            System.out.println("身份证月份无效");
            return false;
        }  
        if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {  
            tipInfo = "身份证日期无效";  
            System.out.println(tipInfo);
            return false;
        }  
           
   
        // 判断地区码是否有效   
        Hashtable areacode = GetAreaCode();  
        //如果身份证前两位的地区码不在Hashtable，则地区码有误  
        if (areacode.get(Ai.substring(0, 2)) == null) {  
            tipInfo = "身份证地区编码错误。";  
            System.out.println(tipInfo);
            return false;
        }  
           
        if(isVarifyCode(Ai,IDStr)==false){  
            tipInfo = "身份证校验码无效，不是合法的身份证号码";  
            System.out.println(tipInfo);
            return false;
        }  
          
           
        return true;  
    }  
       
       
     /* 
      * 判断第18位校验码是否正确 
     * 第18位校验码的计算方式：  
        　　1. 对前17位数字本体码加权求和  
        　　公式为：S = Sum(Ai * Wi), i = 0, ... , 16  
        　　其中Ai表示第i个位置上的身份证号码数字值，Wi表示第i位置上的加权因子，其各位对应的值依次为： 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2  
        　　2. 用11对计算结果取模  
        　　Y = mod(S, 11)  
        　　3. 根据模的值得到对应的校验码  
        　　对应关系为：  
        　　 Y值：     0  1  2  3  4  5  6  7  8  9  10  
        　　校验码： 1  0  X  9  8  7  6  5  4  3   2 
     */  
    private static boolean isVarifyCode(String Ai,String IDStr) {  
         String[] VarifyCode = { "1", "0", "X", "9", "8", "7", "6", "5", "4","3", "2" };  
         String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7","9", "10", "5", "8", "4", "2" };  
         int sum = 0;  
         for (int i = 0; i < 17; i++) {  
            sum = sum + Integer.parseInt(String.valueOf(Ai.charAt(i))) * Integer.parseInt(Wi[i]);  
         }  
         int modValue = sum % 11;  
         String strVerifyCode = VarifyCode[modValue];  
         Ai = Ai + strVerifyCode;  
         if (IDStr.length() == 18) {  
             if (Ai.equals(IDStr) == false) {  
                 return false;  
                   
             }  
         }   
         return true;  
    }  
       
   
    /** 
     * 将所有地址编码保存在一个Hashtable中     
     * @return Hashtable 对象 
     */  
      
    private static Hashtable GetAreaCode() {  
        Hashtable hashtable = new Hashtable();  
        hashtable.put("11", "北京");  
        hashtable.put("12", "天津");  
        hashtable.put("13", "河北");  
        hashtable.put("14", "山西");  
        hashtable.put("15", "内蒙古");  
        hashtable.put("21", "辽宁");  
        hashtable.put("22", "吉林");  
        hashtable.put("23", "黑龙江");  
        hashtable.put("31", "上海");  
        hashtable.put("32", "江苏");  
        hashtable.put("33", "浙江");  
        hashtable.put("34", "安徽");  
        hashtable.put("35", "福建");  
        hashtable.put("36", "江西");  
        hashtable.put("37", "山东");  
        hashtable.put("41", "河南");  
        hashtable.put("42", "湖北");  
        hashtable.put("43", "湖南");  
        hashtable.put("44", "广东");  
        hashtable.put("45", "广西");  
        hashtable.put("46", "海南");  
        hashtable.put("50", "重庆");  
        hashtable.put("51", "四川");  
        hashtable.put("52", "贵州");  
        hashtable.put("53", "云南");  
        hashtable.put("54", "西藏");  
        hashtable.put("61", "陕西");  
        hashtable.put("62", "甘肃");  
        hashtable.put("63", "青海");  
        hashtable.put("64", "宁夏");  
        hashtable.put("65", "新疆");  
        hashtable.put("71", "台湾");  
        hashtable.put("81", "香港");  
        hashtable.put("82", "澳门");  
        hashtable.put("91", "国外");  
        return hashtable;  
    }  
   
    /** 
     * 判断字符串是否为数字,0-9重复0次或者多次    
     * @param strnum 
     * @return 
     */  
    private static boolean isNumeric(String strnum) {  
        Pattern pattern = Pattern.compile("[0-9]*");  
        Matcher isNum = pattern.matcher(strnum);  
        if (isNum.matches()) {  
            return true;  
        } else {  
            return false;  
        }  
    }  
   
    /** 
     * 功能：判断字符串出生日期是否符合正则表达式：包括年月日，闰年、平年和每月31天、30天和闰月的28天或者29天 
     *  
     * @param string 
     * @return 
     */  
    public static boolean isDateA(String strDate) {  
       
        Pattern pattern = Pattern  
                .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))?$");  
        Matcher m = pattern.matcher(strDate);  
        if (m.matches()) {  
            return true;  
        } else {  
            return false;  
        }  
    }  

}
