package com.webmos.framework.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrbwmxx.framework.attachment.modal.Attachment;
import com.hrbwmxx.framework.attachment.modal.AttachmentsView;
import com.webmos.framework.dao.DataMapper;
import com.webmos.framework.dao.ModelDMBMapper;
import com.webmos.framework.dao.ModelMapper;
import com.webmos.framework.model.AdvancedSearch;
import com.webmos.framework.model.CodeCollection;
import com.webmos.framework.model.CodeSql;
import com.webmos.framework.model.DataModel;
import com.webmos.framework.model.FormModel;
import com.webmos.framework.model.ModelDMB;
import com.webmos.framework.model.ModelTable;
import com.webmos.framework.model.Page;
import com.webmos.framework.model.Result;
import com.webmos.framework.model.ResultEntity;
import com.webmos.framework.model.VerifyEntity;
import com.webmos.framework.model.VerifyResult;
import com.webmos.framework.service.IDataService;
import com.webmos.framework.util.ModelValidator;
import com.webmos.framework.util.PropertiesUtil;
import com.webmos.framework.vo.DataModelVo;


@Service("DataServiceImpl")
public class DataServiceImpl implements IDataService {
	
	public static Logger logger = LoggerFactory.getLogger(DataServiceImpl.class);
	@Autowired
	private DataMapper dataMapper;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ModelDMBMapper modelDMBMapper;
	
	/**
	 * 返回模块列表数据
	 */
	public Result queryListData(Page page, DataModel dataModel,boolean isDisplay) {
		
		DataModelVo result =new DataModelVo();
		
		//返回模块配置信息
		ModelTable modInfo = queryModeInfo(dataModel.getMoid());
		//将模块的表名设置给DataModel
		dataModel.setTableName(modInfo.getModel_Table());
		//查询模块未隐藏的字段，以逗号分割
		String noHiddenColumns = modelMapper.queryNotHiddenlColumns(dataModel.getMoid());
		dataModel.setNotHiddenColumns(noHiddenColumns);
		//设置分页数量
		if(page.getEndIndex() < 1000000) {
			page.setPageSize(modInfo.getModel_pageSize());
		}
		
		//设置自定义排序代码片段
		dataModel.setCustomOrderBy(modInfo.getModel_order());
		
		//取出指定模块内，启用高级查询条件的数据模型
		List<AdvancedSearch> adsearchmode = modelMapper.queryAdvancedSearchMode(dataModel.getMoid());
		dataModel.setAdvancedSearchList(adsearchmode);
		
		//将（用户提交的查询条件map）与（高级查询条件模型）进行匹配，过滤掉非法查询条件(不包含在模型中的查询字段)，并将查询值放入查询模型中
		//该步骤必须在高级查询模型放入DataModel之后使用；
		dataModel.CheckContrast();
		//处理前端用户的排序信息，先检查，检查后存储到dataModel中
		orderBycheck(dataModel);
		
		//获取模型与代码表的关系映射
		List<CodeSql> codeSQLList = modelDMBMapper.queryCodeSQLList(dataModel.getMoid());
		
		//查询数据条数
		int totalCount = dataMapper.queryDataCount(page, dataModel);
		List<Map<String, String>> datalist = dataMapper.queryDataList(page, dataModel,codeSQLList);

		List<Map<String, String>> datalist_temp = new ArrayList<Map<String, String>>();
		//将checkbox的值带回前台，这一步只有特殊的查询方法才会有的
		if(isDisplay){
			//借助codeSQLList中取出类型为checkbox的实体信息，主要想取出字段名，看那个字段是checkbox类型的，存入临时数组中
			List<String> checkboxFields = new ArrayList<String>();
			for(CodeSql codesql : codeSQLList){
				if(codesql.getModel_formType().equals("checkbox")){
					//取出字段信息,存入临时变量中
					checkboxFields.add(codesql.getModel_field());
				}
			}
			//判断当前列表中存在checkbox字段，再进行代码表集合查询，如果先查询会浪费资源
			if(checkboxFields.size()>0){
				List<CodeCollection> dmbs = modelDMBMapper.queryCodeCollection(codeSQLList);
				//遍历代码表集合数据，如果是checkbox类型属性使用的代码表，便开始循环list装入数据；
				for(CodeCollection dmb : dmbs){
					if(dmb.getFormType().equals("checkbox")){
						for(Map<String, String> temp :datalist){
							//返回数据中的checkbox代码
							String sjz = temp.get(dmb.getId());
							if(sjz!=null){
								String[] sjzs = sjz.split(",");
								StringBuffer sjz_value =new StringBuffer();
								StringBuffer sjz_text =new StringBuffer();
								List<ModelDMB> sjzdmb = dmb.getDmbList();
								for(String _sjz : sjzs){
									for(ModelDMB dm : sjzdmb){
										if(dm.getValue().equals(_sjz)){
											//为了保证代码值与显示值数组相等，将数据重新放置
											//如果前端显示的代码和数据库不同，说明有没对应上的代码
											sjz_text.append(","+dm.getText());	
											sjz_value.append(","+_sjz);
										}
									}
								}
								//如果代码匹配成功
								if(sjz_value.toString().length()>0){
									temp.put(dmb.getId(), sjz_value.toString().substring(1,sjz_value.length()));
									temp.put(dmb.getId()+"Name", sjz_text.toString().substring(1,sjz_text.length()));
								}
								
							}
							datalist_temp.add(temp);
						}
						datalist = datalist_temp;
					}
				}
				//temp.get("");
				//for(CodeCollection dmb : dmbs){
				//	dmb.getId().equals()
				//}
			}
		}
		result.setRows(datalist);
		result.setTotalCount(totalCount);
		return result;
	}

	/**
	 * 返回一条数据，普通方法，无法转换代码值
	 */
	public  Map<String, Object> queryViewData(DataModel dataModel) {
		
		//从mo_sys模块配置中获取分页数、表名
		ModelTable modInfo = queryModeInfo(dataModel.getMoid());
		//设置查询条件中的表
		dataModel.setTableName(modInfo.getModel_Table());
		//设置查询主键和查询的值
		dataModel.setTable_key(modInfo.getModel_Key());
		dataModel.setTable_key_value(dataModel.getQueryFiledMap().get(modInfo.getModel_Key()));
		//设置查询返回的form表单数据列
		String queryFormColumns = modelMapper.queryFormColumns(dataModel.getMoid());
		dataModel.setFromColumns(queryFormColumns);
		
		Map<String, Object> resultMap = dataMapper.queryDataView(dataModel);
		
		if(resultMap!=null){
			resultMap.put("errcode", "0");
			resultMap.put("errmsg", "操作成功");	
		}else{
			resultMap = new HashMap<String,Object>();
			resultMap.put("errcode", "200");
			resultMap.put("errmsg", "操作失败,未找到指定KEY的数据");	
		}

		
		return resultMap;
	}
	
	/**
	 * 返回一条clob数据
	 */
	public  String queryDataClob(String clobColumnName,DataModel dataModel) {
		
		//从mo_sys模块配置中获取分页数、表名
		ModelTable modInfo = queryModeInfo(dataModel.getMoid());
		//设置查询条件中的表
		dataModel.setTableName(modInfo.getModel_Table());
		//设置查询主键和查询的值
		dataModel.setTable_key(modInfo.getModel_Key());
		dataModel.setTable_key_value(dataModel.getQueryFiledMap().get(modInfo.getModel_Key()));
		//设置clob数据列
		dataModel.setFromColumns(clobColumnName);
		
		String clobStr = dataMapper.queryDataClob(dataModel);

		return clobStr;
	}
	
	
	/**
	 * 返回一条数据，图片的值转为数组
	 */
	public  Map<String, Object> queryViewDataImger(DataModel dataModel) {
		
		//从mo_sys模块配置中获取分页数、表名
		ModelTable modInfo = queryModeInfo(dataModel.getMoid());
		//设置查询条件中的表
		dataModel.setTableName(modInfo.getModel_Table());
		//设置查询主键和查询的值
		dataModel.setTable_key(modInfo.getModel_Key());
		dataModel.setTable_key_value(dataModel.getQueryFiledMap().get(modInfo.getModel_Key()));
		//设置查询返回的form表单数据列
		String queryFormColumns = modelMapper.queryFormColumns(dataModel.getMoid());
		dataModel.setFromColumns(queryFormColumns);
		
		Map<String, Object> resultMap = dataMapper.queryDataView(dataModel);
		
		
		String basePath="";
		try {
			Properties properties = PropertiesUtil.getKey("system.properties");
			basePath=properties.getProperty("basePath");
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<FormModel> formModel = modelMapper.queryFormMode(dataModel.getMoid());
		for(FormModel fmod : formModel) {
			//List<String> values = new ArrayList<String>();
			if("img".equals(fmod.getFormType())) {
				String field = fmod.getField();
				String value = resultMap.get(field)+"";
				String[] values = value.split(",");
				
				List<AttachmentsView> attachmentsViews = new ArrayList<AttachmentsView>();
				for(int i=0;i<values.length;i++) {
					String filedownpath = basePath+"/Attachment/attachmentDownload.do?attachId="+values[i];
					AttachmentsView av = new AttachmentsView();
					av.setAttachId(values[i]);
					av.setUrl(filedownpath);
					attachmentsViews.add(av);
				}
				//替换img类型字段的值
				resultMap.put(field, attachmentsViews);
			}
		}
		
		if(resultMap!=null){
			resultMap.put("errcode", "0");
			resultMap.put("errmsg", "操作成功");	
		}else{
			resultMap = new HashMap<String,Object>();
			resultMap.put("errcode", "200");
			resultMap.put("errmsg", "操作失败,未找到指定KEY的数据");	
		}

		
		return resultMap;
	}
	/**
	 * 返回一条数据，代码将被转换为显示值
	 * 目前转换值支持select/radia/checkbox
	 */
	public  Map<String, String> queryViewDataDisplay(DataModel dataModel) {
		
		//从mo_sys模块配置中获取分页数、表名
		ModelTable modInfo = queryModeInfo(dataModel.getMoid());
		//设置查询条件中的表
		dataModel.setTableName(modInfo.getModel_Table());
		//设置查询主键和查询的值
		dataModel.setTable_key(modInfo.getModel_Key());
		dataModel.setTable_key_value(dataModel.getQueryFiledMap().get(modInfo.getModel_Key()));
		//设置查询返回的form表单数据列
		String queryFormColumns = modelMapper.queryFormColumns(dataModel.getMoid());
		dataModel.setFromColumns(queryFormColumns);
		//获取模型与代码表的关系映射
		List<CodeSql> codeSQLList = modelDMBMapper.queryCodeSQLList(dataModel.getMoid());
		//查询数据，返回map
		Map<String, String> resultMap = dataMapper.queryDataViewDisplay(dataModel,codeSQLList);
		//=====================================处理checkbox值开始===========================================
		//将返回数据中的checkbox类型列对应的值替换
		//借助codeSQLList集合，从中取出类型为checkbox的实体信息，主要想取出字段名，看那个字段是checkbox类型的，存入临时数组中
		List<String> checkboxFields = new ArrayList<String>();
		for(CodeSql codesql : codeSQLList){
			if(codesql.getModel_formType().equals("checkbox") || codesql.getModel_formType().equals("img") || codesql.getModel_formType().equals("file")){
				checkboxFields.add(codesql.getModel_field());
			}
		}
		//如果当前返回数据中包含checkbox类型字段,上一步遍历目的就是不像没有checkbox字段时还获取dmb集合。
		if(checkboxFields.size()>0){
			List<CodeCollection> codeCollectionlist = modelDMBMapper.queryCodeCollection(codeSQLList);
			for(String cboxfield : checkboxFields){
				if(resultMap.get(cboxfield)!=null && !resultMap.get(cboxfield).equals("")){
					String[] values = resultMap.get(cboxfield).toString().split(",");
					StringBuffer valuestrb = new StringBuffer();
					//找出当前字段对应的代码表
					for(CodeCollection codeCollection : codeCollectionlist){
						if(codeCollection.getId().equals(cboxfield)){
							List<ModelDMB> dmblist = codeCollection.getDmbList();
							//循环代码表与字段中的值对应
							for(String value : values){
								for(ModelDMB dmb : dmblist){
									if(dmb.getValue().equals(value)){
										valuestrb.append(","+dmb.getText());
									}
								}
							}
							break;
						}
					}
					if(valuestrb.toString().length()>0){
						resultMap.put(cboxfield+"Name", valuestrb.toString().substring(1,valuestrb.toString().length()));
					}
				}else{
					//注意：如果该字段不存在可能是mybatis机制问题，可以直接增加一个空值返回给前端；
					resultMap.put(cboxfield,"");
					resultMap.put(cboxfield+"Name","");
				}
			}
		}
		//=====================================处理checkbox值结束===========================================
		
		//设置返回值，前端只接收errcode为0的值，所以这里设置一次
		resultMap.put("errcode", "0");
		resultMap.put("errmsg", "操作成功");
		return resultMap;
	}

	/**
	 * 新增一条数据
	 */
	public Result addOneData(DataModel dataModel) {
		Result result =new ResultEntity();
		//从mo_sys模块配置中获取分页数、表名
		ModelTable modInfo = queryModeInfo(dataModel.getMoid());
		//设置查询条件中的表
		dataModel.setTableName(modInfo.getModel_Table());
		
		//取出指定模块内，表单显示为"显示"的字段模型
		List<FormModel> formModels = modelMapper.queryFormMode(dataModel.getMoid());
		//处理form表单模型，将用户输入值植入到模型中
		List<FormModel> formModel_temp = new ArrayList<FormModel>();
		for(FormModel formModel :formModels){
			if(dataModel.getQueryFiledMap().get(formModel.getField())!=null){
				formModel.setColumn_value(dataModel.getQueryFiledMap().get(formModel.getField()));
				formModel_temp.add(formModel);
			}
		}
		//添加主键到新增模型中（自动生成UUID）
		//如果主键名称是WID,则新增数据的主键为自动生成UUID
		//如果主键名称非WID,则不做任何处理，使用表单中指定的主键作为ID，这里不做任何处理
		//if(modInfo.getModel_Key().equals("WID") || modInfo.getModel_Key().equals("wid")){
		//	FormModel primaryFormModel = new FormModel();
		//	primaryFormModel.setField(modInfo.getModel_Key());
		//	primaryFormModel.setColumn_value(java.util.UUID.randomUUID().toString());
		//	formModel_temp.add(primaryFormModel);
		//}
		//添加主键到新增模型中数据中（自动生成UUID）
		//如果前端提交的数据中，模块中指定的主键没有值，则自动生成UUID主键
		if(dataModel.getQueryFiledMap().get(modInfo.getModel_Key())==null){
			FormModel primaryFormModel = new FormModel();
			primaryFormModel.setField(modInfo.getModel_Key());
			primaryFormModel.setColumn_value(java.util.UUID.randomUUID().toString());
			formModel_temp.add(primaryFormModel);
		}
		
		//添加外键到新增模型中(前端获取)
		if(modInfo.getModel_foreign()!=null && !modInfo.getModel_foreign().equals("")&& !modInfo.getModel_foreign().equals("not")){
			FormModel foreignFormModel = new FormModel();
			foreignFormModel.setField(modInfo.getModel_foreign());
			foreignFormModel.setColumn_value(dataModel.getQueryFiledMap().get(modInfo.getModel_foreign()));
			formModel_temp.add(foreignFormModel);
		}
		dataModel.setFormModel(formModel_temp);
		logger.info("前端提交表单数:"+formModels.size());
		logger.info("字段安全校验合格数:"+formModel_temp.size()+"-2(主键与外键)");
		
		//合法性与格式校验(利用模型工具类进行数据校验)
		List<VerifyEntity> verifyEntity = modelMapper.queryVerifyEntityModel(dataModel.getMoid());
		VerifyResult verifyResult = ModelValidator.checkModelDate(verifyEntity, dataModel.getQueryFiledMap());
		if(verifyResult.isPassed()){
			//保存data数据
			int insert_state= dataMapper.addOneData(dataModel);
			//设置返回值
			if(insert_state==1){
				result.setErrcode("0");
				result.setErrmsg("操作成功");
			}else{
				result.setErrcode("500");
				result.setErrmsg("操作失败");
			}
		}else{
			result.setErrcode("500");
			result.setErrmsg(verifyResult.getWrongReason());
		}
		
		return result;
	}
	
	/**
	 * 删除一条数据
	 */
	public Result deleteOneData(DataModel dataModel) {
		Result result =new ResultEntity();
		//从mo_sys模块配置中获取分页数、表名
		ModelTable modInfo = queryModeInfo(dataModel.getMoid());
		logger.info("modInfo.getModel_Table():"+modInfo.getModel_Table());
		//设置查询条件中的表
		dataModel.setTableName(modInfo.getModel_Table());
		//设置查询主键和删除的值
		dataModel.setTable_key(modInfo.getModel_Key());
		dataModel.setTable_key_value(dataModel.getQueryFiledMap().get(modInfo.getModel_Key()));
		
		int delete_state = dataMapper.deleteOneData(dataModel);
		if(delete_state==1){
			result.setErrcode("0");
			result.setErrmsg("操作成功");
		}else{
			result.setErrcode("500");
			result.setErrmsg("操作失败");
		}
		
		return result;
	}
	
	/**
	 * 更新一条数据
	 */
	public  Result updateOneData(DataModel dataModel) {
		Result result =new ResultEntity();
		//从mo_sys模块配置中获取分页数、表名
		ModelTable modInfo = queryModeInfo(dataModel.getMoid());
		logger.info("modInfo.getModel_Table():"+modInfo.getModel_Table());
		//设置查询条件中的表
		dataModel.setTableName(modInfo.getModel_Table());
		//设置查询主键和更新的值
		dataModel.setTable_key(modInfo.getModel_Key());
		dataModel.setTable_key_value(dataModel.getQueryFiledMap().get(modInfo.getModel_Key()));
		
		//设置更新关系
		//取出指定模块内，表单显示为"显示"的字段模型
		List<FormModel> formModel = modelMapper.queryFormMode(dataModel.getMoid());
		//处理form表单模型，将用户输入值植入到模型中
		List<FormModel> formModel_temp = new ArrayList<FormModel>();
		for(int i=0;i<formModel.size();i++){
			FormModel fm = formModel.get(i);
			if(dataModel.getQueryFiledMap().get(fm.getField())!=null){
				fm.setColumn_value(dataModel.getQueryFiledMap().get(fm.getField()));
				//主键不能作为更新列
				if(!fm.equals(modInfo.getModel_Key())){
					formModel_temp.add(fm);
				}
			}
		}
		dataModel.setFormModel(formModel_temp);
		logger.info("表单数:"+formModel.size());
		logger.info("合格数"+formModel_temp.size());
		
		//进行数据更新
		int update_state = dataMapper.updateOneData(dataModel);
		
		if(update_state==1){
			result.setErrcode("0");
			result.setErrmsg("操作成功");
		}else{
			result.setErrcode("500");
			result.setErrmsg("操作失败");
		}
		
		return result;
	}
	
	/**
	 * 对用户提交到后台的排序字段进行检查处理
	 * 如果合法值，将排序字段和排序方式放入dataModel中
	 * @param dataModel
	 */
	private void orderBycheck(DataModel dataModel){
		String orderBystr = dataModel.getQueryFiledMap().get("orderBy");
		if(orderBystr!=null){
			String[] params = orderBystr.split(" ");
			if(params.length==2){
				String order_field = params[0];
				String order_ascdesc = params[1];
				//如果排序字段中的第0位的字段，在未隐藏的字段中存在并且>=0,说名这个字段是合法的，如果不存在算做非法排序字段
				//只有合法的排序字段，才会将排序字段放到datamodel类中，mapper查询中才会使用
				if(dataModel.getNotHiddenColumns().indexOf(order_field)>=0){
					dataModel.setOrderBy_column(order_field);
				}
				//判断排序方式的值，是否是asc或者desc，如果不是这两个值中的任意一个，算作非法排序方式
				//只有合法的排序字段，才会将排序字段放到datamodel类中，mapper查询中才会使用
				if(order_ascdesc.equals("asc") || order_ascdesc.equals("desc")){
					dataModel.setOrderBy_ascdesc(order_ascdesc);
				}
			}
		}
	}
	
	/**
	 * 根据模块ID获取模块信息
	 * @param moid
	 * @return
	 */
	public ModelTable queryModeInfo(String moid){
		return  modelMapper.queryModeInfo(moid);
	}
	
	
	
	
	
}

