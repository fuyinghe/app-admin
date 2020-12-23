package com.hrbwmxx.framework.util;

import java.util.UUID;

/**
 * 项目静态常量
 * @author lijingyu
 *
 */
public class Constant {
	//***************************Oracle 参数配置*****************************
	
	/**
	 * 数据库
	 * 逻辑删除标识，sys_state。1有效，0无效
	 */
	public static final int SYS_STATE_0=0; 
	/**
	 * 数据库
	 * 逻辑删除标识，sys_state。1有效，0无效
	 */
	public static final int SYS_STATE_1=1; 
	//***************************FILE 参数配置*****************************
 
	/**
	 * 图片类型
	 */
	public static String IMAGETYPE="jepg,jpg,png"; 
	/**
	 * 文件下载路径，相对路径
	 */
	public static String downUrl="fj/download.do?fjId=";
	/**
	 * 图片预览路径
	 */
	public static String  showImg2Url="fj/showImg.do?fjId=";
	//***************************ERROR 参数配置*****************************
	/**
	 * 未知错误
	 */
	public static final String ERRCODE_403="403"; 
	/**
	 * 传入参数错误
	 */
	public static final String ERRCODE_310="310"; 

	/**
	 * 数据库无此实例
	 */
	public static final String ERRCODE_320="320"; 
	/**
	 * 删除错误
	 * 数据已经关联业务数据
	 */
	public static final String ERRCODE_330="330"; 
	//***************************Excel 参数配置*****************************
	/**
	 * 标识EXCEL数据是2003L-
	 */
	public final static String EXCEL2003L =".xls";  
	/**
	 * 标识EXCEL数据是2007U+
	 */
	public final static String EXCEL2007U =".xlsx"; 
	/**
	 * 导入数据与模板不匹配，页面响应的信息
	 */
	public final static String EXCEL_ERROR_TEMPLATE_MSG ="该excel数据模板与数据库模板匹配不上!"; 
	/**
	 * 解析的文件格式有误
	 */
	public final static String EXCEL_ERROR_EXCEPTION_MSG ="解析的文件格式有误!"; 
	/**
	 * 标识EXCEL导出模板使用
	 * 当错误达到这个条数以上不再收集数据
	 */
	public final static int    EXCEL_ERROR_SIZE=10;
	//***************************Oracle 静态方法配置**************************
	/**
	 * 
	* @MethodName: getUUID 
	* @description : 获取UUID
	* @author：lijingyu 
	* @date： 2018年1月24日 下午2:04:34
	* @return String
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	/**
	 * 
	* @MethodName: getRandom 
	* @description : 获取3位随机数
	* @author：liuweiwei
	* @date： 2018年2月6日 下午2:04:34
	* @return iint
	 */
	
	public static int getRandom() {
		return (int)(Math.random()*900)+100;
	}
}
