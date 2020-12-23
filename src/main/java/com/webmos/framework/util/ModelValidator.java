package com.webmos.framework.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.webmos.framework.model.VerifyEntity;
import com.webmos.framework.model.VerifyResult;

/**
 * 模型验证器
 * @author GRMa
 */
public class ModelValidator {
	/**
	 * 
	* @MethodName: checkModelDate 
	* @description : 根据校验规则校验数据
	* @author：GRMa 
	* @date： 2018年3月5日 下午1:18:57
	* @param verifyEntity(校验规则)
	* @param dataMap(单条数据)
	* @return VerifyResult
	 */
	public static VerifyResult checkModelDate(List<VerifyEntity> verifyEntity,Map<String,String> dataMap){
		VerifyResult verrs = new VerifyResult();
		if(verifyEntity!=null && dataMap!=null){
			for(VerifyEntity ver : verifyEntity){
				System.out.println(ver.toString());
				String fieldValue = dataMap.get(ver.getField());
				if(fieldValue==null) fieldValue="";
				
				//模型检查
				//if(ver.getValueType()==null){
				//	verrs.setPassed(false);
				//	verrs.setWrongReason(ver.getTitle()+"的模型检查类型未配置");
				//	break;
				//}
				
				//第一步校验必填项
				if(ver.getIsRequired().equals("1")){
					//必填字段为null或者为空
					if(fieldValue.equals("")){
						verrs.setPassed(false);
						verrs.setWrongReason("["+ver.getTitle()+"]不可为空值");
						break;
					}
				}
				
				//第二步，根据检查类型对数据进行校验
				//字符长度校验
				if(ver.getValueType()==null || ver.getValueType().equals("") 
						|| ver.getValueType().equals("string")
						|| ver.getValueType().equals("time")
						|| ver.getValueType().equals("date")
						|| ver.getValueType().equals("datetime")
						|| ver.getValueType().equals("mobile")
						|| ver.getValueType().equals("email")
						|| ver.getValueType().equals("only")
						|| ver.getValueType().equals("idcard")){
					//System.out.println(fieldValue);
					//未标记校验类型(默认类型，可理解为字符串)
					int maxsize= ver.getMaxSize();
					int minsize= ver.getMinSize();
					//输入值过小或者过大
					if(fieldValue.length()<minsize ){
						verrs.setPassed(false);
						verrs.setWrongReason("["+ver.getTitle()+"]长度应大于"+minsize+"字符");
						break;
					}
					if(fieldValue.length()>maxsize){
						verrs.setPassed(false);
						verrs.setWrongReason("["+ver.getTitle()+"]长度应小于"+maxsize+"字符");
						break;
					}
				}
				
				//整数型校验
				if(ver.getValueType().equals("int")){
					//值是否为int型
					boolean isint = RegexUtils.isInt(fieldValue);
					if(!isint){
						verrs.setPassed(false);
						verrs.setWrongReason("["+ver.getTitle()+"]格式不合法，应该为整数");
						break;
					}
					
					int value = RegexUtils.toInt(fieldValue);
					int maxvalue= Integer.parseInt(ver.getMaxValue());
					int minvalue= Integer.parseInt(ver.getMinValue());;
					if(value>maxvalue){
						verrs.setPassed(false);
						verrs.setWrongReason("["+ver.getTitle()+"]数值，应该小于"+maxvalue);
						break;
					}
					if(value<minvalue){
						verrs.setPassed(false);
						verrs.setWrongReason("["+ver.getTitle()+"]数值，应该大于"+minvalue);
						break;
					}
				}
				
				//数字型校验
				if(ver.getValueType().equals("number")){
					//值是否为number型
					boolean isfloat = RegexUtils.isFloat(fieldValue);
					if(!isfloat){
						verrs.setPassed(false);
						verrs.setWrongReason("["+ver.getTitle()+"]格式不合法，应该为整数或小数");
						break;
					}
					
					 Float value = RegexUtils.toFloat(fieldValue);
					int maxvalue= Integer.parseInt(ver.getMaxValue());
					int minvalue= Integer.parseInt(ver.getMinValue());;
					if(value>maxvalue){
						verrs.setPassed(false);
						verrs.setWrongReason("["+ver.getTitle()+"]数值，应该小于"+maxvalue);
						break;
					}
					if(value<minvalue){
						verrs.setPassed(false);
						verrs.setWrongReason("["+ver.getTitle()+"]数值，应该大于"+minvalue);
						break;
					}
				}
				
				//身份证idcard
				/*if(!fieldValue.equals("") && ver.getValueType().equals("idcard")){
					//值是否为number型
					boolean isIdcard = RegexUtils.IDCardValidate(fieldValue);
					if(!isIdcard){
						verrs.setPassed(false);
						verrs.setWrongReason("["+ver.getTitle()+"]格式不合法，身份证格式不正确，例如 15/18位身份证号");
						break;
					}
				}*/
				
				//email校验
				if(!fieldValue.equals("") && ver.getValueType().equals("email")){
					//值是否为email型
					boolean isEmail = RegexUtils.isEmail(fieldValue);
					if(!isEmail){
						verrs.setPassed(false);
						verrs.setWrongReason("["+ver.getTitle()+"]格式不合法，邮箱格式不正确，例如 abc@qq.com");
						break;
					}
				}
				
				//mobile校验
				if(!fieldValue.equals("") && ver.getValueType().equals("mobile")){
					//值是否为email型
					boolean isMobile = RegexUtils.checkMobile(fieldValue);
					if(!isMobile){
						verrs.setPassed(false);
						verrs.setWrongReason("["+ver.getTitle()+"]格式不合法，电话格式不正确，例如 18600001111");
						break;
					}
				}
				
				//date校验
				if(!fieldValue.equals("") && ver.getValueType().equals("date")){
					//值是否为date型
					boolean isdate = RegexUtils.isDate(fieldValue);
					if(!isdate){
						verrs.setPassed(false);
						verrs.setWrongReason("["+ver.getTitle()+"]格式不合法，日期格式不正确，例如 2013-08-09");
						break;
					}
				}
				
				//time校验
				if(!fieldValue.equals("") && ver.getValueType().equals("time")){
					//值是否为time型
					boolean istime = RegexUtils.isTime(fieldValue);
					if(!istime){
						verrs.setPassed(false);
						verrs.setWrongReason("["+ver.getTitle()+"]格式不合法，日期格式不正确，例如 12:12:12");
						break;
					}
				}
				
				//datetime校验
				if(!fieldValue.equals("") && ver.getValueType().equals("datetime")){
					//值是否为datetime型
					boolean isdatetime = RegexUtils.isDateTime(fieldValue);
					if(!isdatetime){
						verrs.setPassed(false);
						verrs.setWrongReason("["+ver.getTitle()+"]格式不合法，日期格式不正确，例如 2013-08-09 17:30:59");
						break;
					}
				}
				
				//only校验
				if(ver.getValueType().equals("only")){
					//值是否为only型
					//未实现
				}
			}
		}
		return verrs;
	}
	/**
	 * 
	* @MethodName: checkModelDates 
	* @description :  批量检查数据
	* @author：lijingyu (修改)
	* @date： 2018年3月5日 上午11:23:21
	* @param verifyEntity
	* @param dataList
	* @param fkDataMap
	* @return VerifyResult
	 */

	public static VerifyResult checkModelDatas(List<VerifyEntity> verifyEntity,List<Map<String,String>> dataList,Map<String,String> foreignValueMap,Map<String,String> columnMap ){
		VerifyResult verrs = new VerifyResult();
		List<Map<String,String>> passList=new ArrayList<Map<String,String>>();
		List<Map<String,String>> wrongList=new ArrayList<Map<String,String>>();
		for(Map<String,String> dataMap:dataList){
			//当前map的值有外键的值，说明是外键，则把外键值加入进去
			dataMap=overlayValue(foreignValueMap, dataMap,columnMap);
			VerifyResult crs = checkModelDate(verifyEntity,dataMap);
			if(!crs.isPassed()){
				//存入错误原因
				String wrongReason=crs.getWrongReason();
				if(dataMap.containsKey("WRONGREASON")) {wrongReason=dataMap.get("WRONGREASON")+";"+crs.getWrongReason();} 
				dataMap.put("WRONGREASON", wrongReason);
				wrongList.add(dataMap);
			}else {
				passList.add(dataMap);
			}
		}
		verrs.setPassList(passList);
		verrs.setWrongList(wrongList);
		return verrs;
	}
	/**
	 * 
	* @MethodName: overlayForeignValue 
	* @description : 将所有涉及外键数据的中文值替换为对应的代码值
	* @author：lijingyu 
	* @date： 2018年3月5日 下午1:22:58
	* @param fkDataMap 外键值关系eg：女：1
	* @param dataMap 具体数据（中文为key，具体值为value）
	* @param columnMap  中文字段key替换为数据库对应的字段
	* @return Map<String,String>
	 */
	public static Map<String,String> overlayValue  (Map<String,String> fkDataMap,Map<String,String> dataMap, Map<String, String> columnMap){
		Map<String,String>  map=new HashMap<String, String>();
		//判断外键map是否存在
		if(null==fkDataMap) {return dataMap;};
		//寻找map中外键对应的代码值
		Iterator<String> iterator = dataMap.keySet().iterator();  
		while (iterator.hasNext()) {
			 String key = iterator.next();  
			 String value=dataMap.get(key);
			 //替换中文key为英文key,存入新map
			 map.put(columnMap.get(key), value);
			 //替换外键值
			 if(fkDataMap.containsKey(value)) {
				 map.put(columnMap.get(key), fkDataMap.get(value));
				 map.put("COLUMN_CN", key);
				 map.put("COLUMN_EN", columnMap.get(key));
			 }
			 
		}
		return map;
		
	}
	
}
