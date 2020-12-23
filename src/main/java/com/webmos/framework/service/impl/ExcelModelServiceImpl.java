package com.webmos.framework.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.webmos.framework.dao.ExcelModelMapper;
import com.webmos.framework.model.DataModel;
import com.webmos.framework.model.ImpModel;
import com.webmos.framework.model.Result;
import com.webmos.framework.model.VerifyEntity;
import com.webmos.framework.model.VerifyResult;
import com.webmos.framework.service.IExcelModelService;
import com.webmos.framework.util.ExcelUtil;
import com.webmos.framework.util.ModelValidator;
import com.webmos.framework.vo.ExcelModelVo;
import com.hrbwmxx.framework.util.Constant;
import com.hrbwmxx.framework.util.TimeUtil;
@Service("ExcelModelServiceImpl")
public class ExcelModelServiceImpl implements IExcelModelService {
	public static Logger logger = LoggerFactory.getLogger(ExcelModelServiceImpl.class);
	 
	@Autowired
	private ExcelModelMapper excelModelMapper;
	//定义全局返回参数
	private ExcelModelVo result =new ExcelModelVo();
	/***
	 * 获得excel标题
	 */
	public String queryExcelTitle(String modid) {
		Map<String, String> map = excelModelMapper.queryBingTable(modid);
		String title=(map.get("MO_NAME")==null|| map.get("MO_NAME")=="" ? TimeUtil.getDay():map.get("MO_NAME"))   ;
		return title;
	}
	/**
	 *下载模板
	 */
	public Workbook downExcelTemplate(String modid)  throws Exception  {
		
		//获得excel标题
		Map<String, String> map = excelModelMapper.queryBingTable(modid);
		String title=(map.get("MO_NAME")==null|| map.get("MO_NAME")=="" ? TimeUtil.getDay():map.get("MO_NAME"))   ;
		//获得excel导出列
		List<ImpModel> list = excelModelMapper.queryImpTempMode(modid);
		if(list==null || list.size()<1){return null;}
		return ExcelUtil.modelExport(title,list);
	}
	/**
	 * 导出数据
	 */
	public Workbook exportExcelTemplateData(DataModel dataModel) {
		// TODO Auto-generated method stub
		return null;
	}
	 
	/**
	 * 
	* @MethodName: importExcelTemplateData 
	* @description : 导入数据
	* @author：lijingyu 
	* @date： 2018年2月28日 上午10:39:06
	* @param file
	* @param modid
	* @return Result
	*/
	
	public Result importExcelTemplateData(MultipartFile file, String modid) throws Exception {
		
    	//读取数据将excel数据
    	InputStream  in=file.getInputStream();
    	List<Map<String, String>> list=ExcelUtil.getDataList(in, file.getOriginalFilename());
    	//检测表格中是否含有数据
    	if(null==list||list.size()<1) {
    		result.setErrcode(Constant.ERRCODE_310);
    		result.setErrmsg(Constant.EXCEL_ERROR_TEMPLATE_MSG);
    		return result;
    	}
    	//1.1查询具体模块对应的表
        Map<String, String> table = excelModelMapper.queryBingTable(modid);
    	//1.2查询对应的表对应外键关系
		List<Map<String, String>> fkMap=excelModelMapper.queryModelFkRrelations(modid);
		//1.3根据对应外键关系查询字段映射关系具体代码值，并处理为map
		List<Map<String, String>>  foreignValueList=excelModelMapper.queryFkValuesMap(fkMap);
		Map<String, String> foreignValueMap=convertList2Map(foreignValueList,"TEXT","VALUE");
		//1.5查询Excel中列与数据库的字段列绑定关系，并处理为map
		List<ImpModel> columns = excelModelMapper.queryImpTempMode(modid);
		Map<String, String> columnMap=convertList2Map(columns);
		//1.6合法性与格式校验(利用模型工具类进行数据校验)校验不通过返回错误数据
		List<VerifyEntity> verifyEntity = excelModelMapper.queryVerifyEntityModel(modid);
		VerifyResult verrs=ModelValidator.checkModelDatas(verifyEntity, list, foreignValueMap,columnMap);
		if(null!=verrs.getWrongList() &&verrs.getWrongList().size()>0) {
			result.setVerifyResult(verrs);
			return result;
		}
		//2.1插入临时表(定义批次，防止在删除数据时候误操作其他数据)
		String uuid="";
		if(columnMap.containsKey("UUID")) {
			uuid=UUID.randomUUID().toString();
		}
		//TODO  从session中取当前操作人
		String czr="当前操作人";
    	//2.1将数据插入模板库数据
        saveTemplateData(table,columns,uuid,czr,modid,verrs.getPassList());
        //3.1将数据迁移到业务表中
        handleData2ModelTable(table,columns,uuid,czr,modid,verrs.getPassList());
        //4.1删除模板表中的数据
        excelModelMapper.deleteModel(table.get("T_TABLE"),uuid,czr);
    	//如果返回list没有数据将错误提示返给前台
    	if(list==null||list.size()<1) {
    		result.setErrcode(Constant.ERRCODE_310);
    		result.setErrmsg(Constant.EXCEL_ERROR_TEMPLATE_MSG);

    	} 
    	return result;
	}
	/**
	 * 
	* @MethodName: handleData2ModelTable 
	* @description : TODO
	* @author：lijingyu 
	* @date： 2018年3月6日 下午3:51:03
	* @param table 表关系（WID, MO_NAME, MO_TABLE, MO_TABLE||'_IMP' T_TABLE, MO_DES）
	* @param columns列关系（包含中英文字段对应关系，是否为主键）
	* @param uuid批次ID
	* @param czr操作人ID
	* @param modid void
	 */
	private void handleData2ModelTable(Map<String, String> table, List<ImpModel> columns, String uuid, String czr,
			String modid,List<Map<String, String>> excelList) {
		/*
		select mzdm, zzmm, tel, sfzjh, qq, xbdm, xm, uuid, czr 
		from t_xsxx_jbxx_imp where exists (select 1 from t_xsxx_jbxx 
		where 1=1 t_xsxx_jbxx_imp.sfzjh=t_xsxx_jbxx.sfzjh)
		*/
		//列字段
		String columnCode=concatSelectColumns(columns);
		//主键Sql片段
		String keySqlCode=concatTableWhereKeysColumns(table,columns);
		//入库修改数据
		List<Map<String, String>>  existsList=excelModelMapper.existsData(columnCode,table.get("T_TABLE"),keySqlCode,czr,uuid);
		if(null!=existsList&&existsList.size()>0) {
			updateModelData(table, columns, uuid, czr, existsList);
			result.setUptList(existsList);
		}
		//入库新数据
		List<Map<String, String>>  addList=excelModelMapper.notExistsData(columnCode,table.get("T_TABLE"),keySqlCode,czr,uuid);
		if(null!=addList&&addList.size()>0) {
			saveModelData(table,columns,uuid,czr,modid,addList);
			result.setAddList(addList);
		}
		
		
	}
 
	 
	/**
	 * 
	* @MethodName: concatTableWhereKeysColumns 
	* @description : 拼凑where条件sql片段
	* eg：
	*   (
	    select 1 from t_xsxx_jbxx 
		where 1=1 t_xsxx_jbxx_imp.sfzjh=t_xsxx_jbxx.sfzjh)
	* @author：lijingyu 
	* @date： 2018年3月6日 下午2:41:30
	* @param table
	* @return String
	 */
	private String concatTableWhereKeysColumns(Map<String, String> table, List<ImpModel> columns) {
		StringBuffer sb=new StringBuffer();
		String modelName = table.get("MO_TABLE");
		String impName = table.get("T_TABLE");
		sb.append("select 1 from "+modelName+" where 1=1 ");
		for( ImpModel obj:columns) {
			if("1".equals(   obj.getIsKey() )) {
				String sqlStr=" and "+modelName+"."+obj.getField()+"="+impName+"."+obj.getField();
				sb.append(sqlStr);
			}
			
		}
		return sb.toString();
	}
	/**
	 * 
	* @MethodName: convertList2Map 
	* @description : TODO
	* @author：lijingyu 
	* @date： 2018年3月6日 上午8:52:30
	* @param columns
	* @return Map<String,String>
	 */
	private Map<String, String> convertList2Map(List<ImpModel> columns) {
		Map<String, String> map=new HashMap<String, String>();
		if(null!=columns) {
			for (int i = 0; i < columns.size(); i++) {
				//中文为key，根据中文检索出英文
				map.put( columns.get(i).getTitle(),columns.get(i).getField());
			}
		}
		return map;
	}
	/**
	 * 
	* @MethodName: convertList2Map 
	* @description : 将list转map
	* @author：lijingyu 
	* @date： 2018年3月5日 下午3:35:55
	* @param foreignValueList
	* @return Map<String,String>
	 */
	private Map<String, String> convertList2Map(List<Map<String, String>> list,String key,String value) {
		Map<String, String> map=new HashMap<String, String>();
		if(null!=list) {
			for (int i = 0; i < list.size(); i++) {
				//key为中文，value为对应的英文
				map.put( list.get(i).get(key),list.get(i).get(value));
			}
		}
		return map;
	}
	/**
	 * 
	* @MethodName: updateModelData 
	* @description : 更新业务表数据
	* @author：lijingyu 
	* @date： 2018年3月1日 下午1:14:16
	* @param bingObj
	* @param columns
	* @param excelMap
	* @param uuid
	* @param czr void
	 */
	private void updateModelData(Map<String, String> table ,List<ImpModel> columns,String uuid,String czr,List<Map<String, String>> dataList) {
		//获得更新的列
		String whereSqlCode=concatTableWhereSqlCode(uuid,czr,columns);
		List<String>  list=concatTableUpdateColumns(table.get("MO_TABLE"),whereSqlCode,columns,dataList);
		//将columnStr循环放入map中
		excelModelMapper.updateTemplateDataFkRrelation(table.get("MO_TABLE"),whereSqlCode,list);
	}
	/**
	 * 
	* @MethodName: concatTableWhereSqlCode 
	* @description : TODO
	* @author：lijingyu 
	* @date： 2018年3月7日 下午4:13:21
	* @param uuid
	* @param czr
	* @param columns
	* @return String
	 */
	private String concatTableWhereSqlCode(String uuid, String czr,List<ImpModel> columns) {
		String whereSqlCode="";
		for( ImpModel obj:columns) {
			if("CZR".equals(obj.getField().toUpperCase())) {//维护操作人
				whereSqlCode=" and CZR='"+czr+"'";
			}else if("UUID".equals( obj.getField().toUpperCase()  )){//维护批次属性
				whereSqlCode+=" and UUID='"+uuid+"'";
				
			}
		}
		return whereSqlCode;
	}

	/**
	 * 
	* @MethodName: concatTableUpdateColumns 
	* @description : TODO
	* @author：lijingyu 
	* @date： 2018年3月1日 下午4:25:40
	* @param columns
	* @param dataList
	* @return List<String>
	 */
	private List<String> concatTableUpdateColumns (String  tableName,String whereSqlCode,List<ImpModel> columns,List<Map<String, String>> dataList ) {
		List<String>  valuesStr=new ArrayList<String>();
	 	//根据传来的模板绑定关系遍历哪些是外键
		for (Map<String, String> row:dataList) {//map是个单个对象，包含一行数据
			StringBuffer sb=new StringBuffer();
			for( ImpModel obj:columns) {
				String value="";
				String type=obj.getCheckType();
				String field=obj.getField();
				
				//if("UUID".equals(field.toUpperCase()) ||"CZR".equals(field.toUpperCase())) {continue;}
				if("date".equals(type)||"datetime".equals(type)) {
					value=obj.getField()+"=to_date('"+ row.get( obj.getField().toUpperCase() )+"','yyyy-MM-dd'),";
				}
				if("int".equals(type)) {
					value=obj.getField()+"="+row.get( obj.getField().toUpperCase() )+",";
				}
				if("".equals(value)) {
					value=obj.getField()+" ='"+row.get( field )+"',";
				}
				sb.append(value);
			}
			valuesStr.add(sb.toString().substring(0, sb.toString().lastIndexOf(","))+";");
		} 
		
		return  valuesStr;
	}
	 

	/**
	 * 
	* @MethodName: saveTemplateData 
	* @description : 根据传入参数列拼凑每列
	* @author：lijingyu 
	* @date： 2018年2月28日 下午5:24:26
	* @param modid
	* @param list
	* @return String
	 */
	private void saveTemplateData(Map<String, String> table,List<ImpModel> columns,
			String uuid,String czr,String modid, List<Map<String, String>> list) {
		//1拼凑列
    	String columnStr=concatTableSelectColumns(columns,czr);
    	//2拼凑值集合
    	List<String> valuesList=concatTableAddValues( czr,uuid,columns,list);
    	//3持久化
    	excelModelMapper.addTemplateData(table.get("T_TABLE"),columnStr,valuesList);
	}
	/**
	 * 
	* @MethodName: saveModelData 
	* @description : 保存模型数据
	* @author：lijingyu 
	* @date： 2018年3月7日 下午3:25:19
	* @param table
	* @param columns
	* @param uuid
	* @param czr
	* @param modid
	* @param list void
	 */
	private void saveModelData(Map<String, String> table,List<ImpModel> columns,
			String uuid,String czr,String modid, List<Map<String, String>> list) {
		//1拼凑列
    	String columnStr=concatTableSelectColumns(columns,"");
    	//2拼凑值集合
    	List<String> valuesList=concatTableAddValues( "",uuid,columns,list);
    	//3持久化
    	excelModelMapper.addTemplateData(table.get("MO_TABLE"),columnStr,valuesList);
	}
	/**
	 * 
	* @MethodName: concatTableSelectColumns 
	* @description : 用于插入数据库时，拼凑的sql字段列
	* @author：lijingyu 
	* @date： 2018年2月28日 下午5:24:22
	* @param columns
	* @param czr  临时表中的操作人
	* @return String
	 */
	private String concatTableSelectColumns( List<ImpModel>  columns,String czr) {
		StringBuffer sb=new StringBuffer();
		for( ImpModel obj:columns) {
			String sqlStr=( StringUtils.isNoneBlank(obj.getField())    ?obj.getField():""   )+",";
			//筛查是否有批次和操作人数据如果有，将上面的值覆盖为外键的值在进行拼凑
			if( "UUID".equals(obj.getField().toUpperCase())) {sqlStr="UUID,";}
			//过滤掉这个关键字
			if( "CZR".equals(obj.getField().toUpperCase())) {sqlStr="CZR,";}
			sb.append(sqlStr);
		}
		String str =sb.toString().substring(0, sb.toString().lastIndexOf(","));
		
		sb=new StringBuffer();
		sb.append(str);
		//若操作人字段为空表示当前的列要插入业务表中，否则就是要插入临时表中
		if(!"".equals(czr)) {
			return sb.toString()+",CZR";
		}else {
			return sb.toString();
		}
		
   }
	/**
	 * 
	* @MethodName: concatTableSelectColumns 
	* @description : 用于查询数据库时，拼凑的sql字段选出的列
	* @author：lijingyu 
	* @date： 2018年2月28日 下午5:24:22
	* @param name
	* @param columns
	* @param isAddImp是否是添加到导入到模板数据库中
	* @return String
	 */
	private String concatSelectColumns( List<ImpModel>  columns) {
		StringBuffer sb=new StringBuffer();
		for( ImpModel obj:columns) {
			String sqlStr=( StringUtils.isNoneBlank(obj.getField())    ?obj.getField():""   )+",";
			//处理
			String type=obj.getCheckType();
			//筛查是否有批次和操作人数据如果有，将上面的值覆盖为外键的值在进行拼凑
			if( "UUID".equals(obj.getField().toUpperCase())) {sqlStr="UUID,";}
			//过滤掉这个关键字
			if( "CZR".equals(obj.getField().toUpperCase())) {continue;}
			if("date".equals(type)||"datetime".equals(type)) {
				sqlStr="to_char("+ obj.getField().toUpperCase() +",'yyyy-MM-dd') AS "+obj.getField().toUpperCase()+",";
			}
			sb.append(sqlStr);
		}
		String str =sb.toString().substring(0, sb.toString().lastIndexOf(","));
		
		sb=new StringBuffer();
		sb.append(str);
		
		return sb.toString();
   }
	/**
	 * 
	* @MethodName: concatTableValues 
	* @description : 根据传入参数列和具体值拼凑每列的值
	* @author：lijingyu 
	* @date： 2018年2月28日 上午10:39:06
	* @param czr 临时表中的操作人，若不是插入临时表需要用空串替换
	* @param columns 字段关系映射
	* @param list 数据集合
	* @return List<String>
	 */
	private List<String> concatTableAddValues( String czr,String uuid, List<ImpModel>  columns, List<Map<String, String>> list ) {
		List<String> valuesList=new ArrayList<String>();
		for(Map<String, String> row:list) {
			StringBuffer valuesStr=new StringBuffer();
			//valuesStr.append(" select ");
			for( ImpModel model:columns) {
				String sqlStr="'"+row.get( model.getField().toUpperCase() )+"',";
				//筛查是否有批次和操作人数据如果有，将上面的值覆盖为外键的值在进行拼凑
				if( "UUID".equals(model.getField().toUpperCase())) {sqlStr="'"+uuid+"',";}
				//过滤掉这个关键字
				 
				if( "CZR".equals(model.getField().toUpperCase())) {continue;}
				//处理
				String type=model.getCheckType();
				if("date".equals(type)||"datetime".equals(type)) {
					sqlStr="to_date('"+row.get( model.getField().toUpperCase() )+"','yyyy-MM-dd'),";
				}
				if("int".equals(type)) {
					sqlStr=row.get( model.getField().toUpperCase() )+",";
				}
				//将sqlStr拼凑到结果
				
				valuesStr.append(sqlStr);
				
			} 
			String sqlValuesCode =valuesStr.toString().substring(0, valuesStr.toString().lastIndexOf(","));
			if(!"".equals(czr)) {sqlValuesCode=sqlValuesCode+",'"+czr+"'";}
			valuesList.add(sqlValuesCode);
		}
		return valuesList;
   }
	 
	
}
