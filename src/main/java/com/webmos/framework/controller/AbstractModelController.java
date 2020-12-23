package com.webmos.framework.controller;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hrbwmxx.framework.util.CsvExportUtil;
import com.webmos.framework.model.DataModel;
import com.webmos.framework.model.Page;
import com.webmos.framework.model.Result;
import com.webmos.framework.model.ResultEntity;
import com.webmos.framework.service.IDataService;
import com.webmos.framework.service.IExcelModelService;
import com.webmos.framework.service.IFileService;
import com.webmos.framework.service.IModelService;
import com.webmos.framework.util.PropertiesUtil;
import com.webmos.framework.vo.DataModelVo;
import com.webmos.framework.util.Constant;

public abstract class AbstractModelController implements IModelController {
	
	public static Logger logger = LoggerFactory.getLogger(AbstractModelController.class);
	
	@Qualifier("ModelServiceImpl")
	@Autowired
	private IModelService modelService;
	@Qualifier("DataServiceImpl")
	@Autowired
	private IDataService dataService;
	@Qualifier("ExcelModelServiceImpl")
	@Autowired
	private IExcelModelService excelModelService;
	//模块绑定ID
	private String modid = "";
	
	/**
	* @MethodName: queryModelMete 
	* @description : 获取绑定模块的Mate结构数据
	* @author：GRMa 
	* @return Result
	 */
	@RequestMapping("version")
	@ResponseBody
	public String version() {
		return "当前版本1.6";
	}

	/**
	* @MethodName: queryModelMete 
	* @description : 获取绑定模块的Mate结构数据
	* @author：GRMa 
	* @return Result
	 */
	@RequestMapping("queryModelMete")
	@ResponseBody
	public Result queryModelMete() {
		return this.queryModelMeteCustom(this.getModid());
	}
	public Result queryModelMeteCustom(String moId) {
		return modelService.queryModelMeteList(moId);
	}
	
	/**
	* @MethodName: queryListData 
	* @description : 获取数据列表List
	* @author：GRMa 
	* @return Result
	 */
	@RequestMapping("queryListData")
	@ResponseBody
	public Result queryListData(Page page, @RequestParam HashMap<String,String>  queryParamFiledMap) {
		return this.queryListDataCustom(this.getModid(), page, queryParamFiledMap);
	}
	
	
	@RequestMapping("exportListData")
	@ResponseBody
	public Result exportListData(HttpServletResponse response,HttpServletRequest request,Page page, @RequestParam HashMap<String,String>  queryParamFiledMap) {
		try {
			request. setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		page.setEndIndex(1000000);
		queryParamFiledMap.put("pageSize", "1000000");
		DataModelVo r = (DataModelVo) this.queryListDataCustom(this.getModid(), page, queryParamFiledMap);
      
      // 构造导出数据
      List<Map<String, Object>> datas = new ArrayList<>();
      String keys = "";
		  Map<String, String> data = ((List<Map<String, String>>)r.getRows()).get(0);
		  for(String key : data.keySet()) { 
			  keys  = keys + key+',';
		  }
		  keys = (String) keys.subSequence(0, keys.length()-1);
		  for(Map<String, String> row : (List<Map<String, String>>)r.getRows()) {
			  for(String key : row.keySet()) {
				  String value = String.valueOf(row.get(key));
				  if(value.length() > 10) {
					  row.put(key, value + "\t") ;
				  }
			  }
		  }
      // 设置导出文件前缀
      String fName = "_";
      // 文件导出
      try {
          OutputStream os = response.getOutputStream();
          CsvExportUtil.responseSetProperties(fName, response);
          CsvExportUtil.doExport((List<Map<String, Object>>)r.getRows(), keys, keys, os);
          os.close();
      } catch (Exception e) {
          return r;
      }
		return null;
	}
	
	public Result queryListDataCustom(String moId,Page page, @RequestParam HashMap<String,String>  queryParamFiledMap) {
		System.out.println(queryParamFiledMap);
		//模型数据临时存储类
		DataModel dataModel = new DataModel();
		//将模块的id注入到DataModel类中，后续会持续使用
		dataModel.setMoid(moId);
		//将前端传入的查询条件塞入DataModel的adsearchParam中
		dataModel.setQueryFiledMap(queryParamFiledMap);
		//调用service类，查询数据
		return dataService.queryListData(page, dataModel,false);
	}
	/**
	* @MethodName: queryListData_Display 
	* @description : 获取数据列表List,可带会checkbox显示值，该方法耗能
	* @author：GRMa 
	* @return Result
	 */
	@RequestMapping("queryListData_Display")
	@ResponseBody
	public Result queryListData_Display(Page page, @RequestParam HashMap<String,String>  queryParamFiledMap) {
		return this.queryListData_DisplayCustom(this.getModid(),page, queryParamFiledMap);
	}
	public Result queryListData_DisplayCustom(String moId,Page page, @RequestParam HashMap<String,String>  queryParamFiledMap) {
		//模型数据临时存储类
		DataModel dataModel = new DataModel();
		//将模块的id注入到DataModel类中，后续会持续使用
		dataModel.setMoid(moId);
		//将前端传入的查询条件塞入DataModel的adsearchParam中
		dataModel.setQueryFiledMap(queryParamFiledMap);
		//调用service类，查询数据
		return dataService.queryListData(page, dataModel,true);
	}

	/**
	* @MethodName: queryViewData 
	* @description : 获取一条数据
	* @author：GRMa 
	* @return Result
	 */
	@RequestMapping("queryViewData")
	@ResponseBody
	public Map<String, Object> queryViewData(@RequestParam HashMap<String,String>  queryParamFiledMap) {
		return this.queryViewDataCustom(this.getModid(),queryParamFiledMap);
	}
	public Map<String, Object> queryViewDataCustom(String moId,HashMap<String,String>  queryParamFiledMap) {
		//模型数据临时存储类
		DataModel dataModel = new DataModel();
		//将模块的id注入到DataModel类中，后续会持续使用
		dataModel.setMoid(moId);
		//将前端传入的查询条件塞入DataModel的adsearchParam中
		dataModel.setQueryFiledMap(queryParamFiledMap);
		return dataService.queryViewData(dataModel);
	}
	/**
	* @MethodName: queryViewData 
	* @description : 获取一条数据
	* @author：GRMa 
	* @return Result
	 */
	@RequestMapping("queryViewDataImger")
	@ResponseBody
	public Map<String, Object> queryViewDataImger(@RequestParam HashMap<String,String>  queryParamFiledMap) {
		return this.queryViewDataImgerCustom(this.getModid(),queryParamFiledMap);
	}
	public Map<String, Object> queryViewDataImgerCustom(String moId,@RequestParam HashMap<String,String>  queryParamFiledMap) {
		//模型数据临时存储类
		DataModel dataModel = new DataModel();
		//将模块的id注入到DataModel类中，后续会持续使用
		dataModel.setMoid(moId);
		//将前端传入的查询条件塞入DataModel的adsearchParam中
		dataModel.setQueryFiledMap(queryParamFiledMap);
		return dataService.queryViewDataImger(dataModel);
	}
	/**
	* @MethodName: queryViewData_Display 
	* @description : 获取一条数据，带显示值的
	* @author：GRMa 
	* @return Result
	 */
	@RequestMapping("queryViewDataDisplay")
	@ResponseBody
	public Map<String, String> queryViewDataDisplay(@RequestParam HashMap<String, String> queryParamFiledMap) {
		return this.queryViewDataDisplayCustom(this.getModid(),queryParamFiledMap);
	}
	public Map<String, String> queryViewDataDisplayCustom(String moId,@RequestParam HashMap<String, String> queryParamFiledMap) {
		//模型数据临时存储类
		DataModel dataModel = new DataModel();
		//将模块的id注入到DataModel类中，后续会持续使用
		dataModel.setMoid(moId);
		//将前端传入的查询条件塞入DataModel的adsearchParam中
		dataModel.setQueryFiledMap(queryParamFiledMap);
		return dataService.queryViewDataDisplay(dataModel);
	}
	
	/**
	* @MethodName: addOneData 
	* @description : 插入一条数据
	* @author：GRMa 
	* @return Result
	 */
	@RequestMapping("addOneData")
	@ResponseBody
	public Result addOneData(@RequestParam HashMap<String,String>  queryParamFiledMap){
		return this.addOneDataCustom(this.getModid(),queryParamFiledMap);
	}
	public Result addOneDataCustom(String moId,@RequestParam HashMap<String,String>  queryParamFiledMap){
		//模型数据临时存储类
		DataModel dataModel = new DataModel();
		//将模块的id注入到DataModel类中，后续会持续使用
		dataModel.setMoid(moId);
		//将前端传入的查询条件塞入DataModel的adsearchParam中
		dataModel.setQueryFiledMap(queryParamFiledMap);
		
		return dataService.addOneData(dataModel);
	}
	
	/**
	* @MethodName: deleteOneData 
	* @description : 删除一条数据，伪删除
	* @author：GRMa 
	* @return Result
	 */
	@RequestMapping("deleteOneData")
	@ResponseBody
	public Result deleteOneData(@RequestParam HashMap<String,String>  queryParamFiledMap) {
		return this.deleteOneDataCustom(this.getModid(),queryParamFiledMap);
	}
	public Result deleteOneDataCustom(String moId,@RequestParam HashMap<String,String>  queryParamFiledMap) {
		//模型数据临时存储类
		DataModel dataModel = new DataModel();
		//将模块的id注入到DataModel类中，后续会持续使用
		dataModel.setMoid(moId);
		//将前端传入的查询条件塞入DataModel的adsearchParam中
		dataModel.setQueryFiledMap(queryParamFiledMap);
		return dataService.deleteOneData(dataModel);
	}
	
	/**
	* @MethodName: updateOneData 
	* @description : 更新一条数据
	* @author：GRMa 
	* @return Result
	 */
	@RequestMapping("updateOneData")
	@ResponseBody
	public Result updateOneData(@RequestParam HashMap<String,String>  queryParamFiledMap) {
		return this.updateOneDataCustom(this.getModid(),queryParamFiledMap);
	}
	public Result updateOneDataCustom(String moId,@RequestParam HashMap<String,String>  queryParamFiledMap) {
		//模型数据临时存储类
		DataModel dataModel = new DataModel();
		//将模块的id注入到DataModel类中，后续会持续使用
		dataModel.setMoid(moId);
		//将前端传入的查询条件塞入DataModel的adsearchParam中
		dataModel.setQueryFiledMap(queryParamFiledMap);
		return dataService.updateOneData(dataModel);
	}

	
/*	 
	*//**
	 * 
	* @MethodName: importExcelTemplateData 
	* @description : 导入excel模板数据
	* @author：lijingyu 
	* @date： 2018年2月12日 下午1:27:40
	* @param file
	* @param response
	* @return
	* @throws Exception Result
	 *//*
   @RequestMapping("importExcelTemplateData")
   @ResponseBody
   public Result importExcelTemplateData( @RequestParam  MultipartFile file,HttpServletResponse response ,String modid)  throws Exception{
	     *//**导入数据操作*//*
	     ExcelModelVo re = excelModelService.importExcelTemplateData(file, modid);
	     *//**生成结果数据流*//*
	     return excelModelService.exportExcelResultData(re);
		  
   }*/
	/**
	 * 
	* @MethodName: exportExcelTemplateData 
	* @description : 导出Excel模板数据
	* @author：lijingyu 
	* @date： 2018年2月12日 下午1:27:21
	* @param response
	* @param queryParamFiledMap void
	 * @throws Exception 
	 */
	@RequestMapping("exportExcelTemplateData")
	public void  exportExcelTemplateData(HttpServletResponse response ,@RequestParam HashMap<String,String>  queryParamMap,String modid) throws Exception{
		//模型数据临时存储类
		DataModel dataModel = new DataModel();
		//将模块的id注入到DataModel类中，后续会持续使用
		dataModel.setMoid(modid);
		//将前端传入的查询条件塞入DataModel的adsearchParam中
		dataModel.setQueryFiledMap(queryParamMap);
		//获得标题
		String title=excelModelService.queryExcelTitle(modid);
		//获得数据
		Workbook workbook =excelModelService.exportExcelTemplateData(dataModel);
		//输出文件
	    if(workbook !=null){
           try
           {
               String fileName =  title+ ".xlsx";
               String headStr = "attachment; filename=\"" + new String( fileName.getBytes("utf-8"),"iso8859-1") + "\"";
               response.setContentType("APPLICATION/OCTET-STREAM");
               response.setHeader("Content-Disposition", headStr);
               OutputStream out = response.getOutputStream();
               workbook.write(out);
               workbook.close();
           }
           catch (IOException e)
           {
               e.printStackTrace();
           }
       }else {
    	   OutputStream out = response.getOutputStream();
    	   out.write(new String("当前模板"+"["+title+"]导出字段未配置,请配置！").getBytes());
    	   out.close();
       }
	}
	/**
	* @MethodName: downExcelTemplate 
	* @description : 下载Excel模板
	* @author：lijingyu 
	* @date： 2018年2月12日 下午1:26:39
	* @param response
	* @param queryParamFiledMap void
	 * @throws Exception 
	 */
	@RequestMapping("downExcelTemplate")
	public void  downExcelTemplate(HttpServletResponse response,String modid) throws Exception{
		//获得标题
		String title=excelModelService.queryExcelTitle(modid);
		//获得数据
		Workbook workbook =excelModelService.downExcelTemplate(modid);
		//输出文件
	    if(workbook !=null){
           try
           {
               String fileName =  title+ ".xlsx";
               String headStr = "attachment; filename=\"" + new String( fileName.getBytes("utf-8"),"iso8859-1") + "\"";
               response.setContentType("APPLICATION/OCTET-STREAM");
               response.setHeader("Content-Disposition", headStr);
               OutputStream out = response.getOutputStream();
               workbook.write(out);
               workbook.close();
           }
           catch (IOException e)
           {
               e.printStackTrace();
           }
       }else {
    	   OutputStream out = response.getOutputStream();
    	   out.write(new String("当前模板"+"["+title+"]导入字段未配置,请配置！").getBytes());
    	   out.close();
       }
	}
	
	public String queryDataClob(String moId,String clobColumnName,HashMap<String,String>  queryParamFiledMap) {
		//模型数据临时存储类
		DataModel dataModel = new DataModel();
		//将模块的id注入到DataModel类中，后续会持续使用
		dataModel.setMoid(moId);
		//将前端传入的查询条件塞入DataModel的adsearchParam中
		dataModel.setQueryFiledMap(queryParamFiledMap);
		return dataService.queryDataClob(clobColumnName, dataModel);
	}
	
	
	public String getModid() {
		return modid;
	}

	public void setModid(String modid) {
		this.modid = modid;
	}


}
