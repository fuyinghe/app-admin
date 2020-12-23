package com.hrbwmxx.framework.fj.controller;

 
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrbwmxx.framework.fj.model.ExcelModel;
import com.hrbwmxx.framework.fj.service.IExcelService;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.framework.util.ExcelUtil;

/**
 * 
* @title: ExcelController 
* @description：表格模板导入导出控制器
* @author： lijingyu
* @date： 2018年1月22日 上午10:09:28
 */
@Controller
@RequestMapping("excel")
public class ExcelController {
	@Autowired
	private IExcelService excelService;
	/**
	 * 
	* @MethodName: importExcel 
	* @description : 导入excel模板数据
	* @author：lijingyu 
	* @date： 2018年1月22日 上午10:17:45
	* @param response
	* @param key void
	 */
   @RequestMapping("importExcel")
   @ResponseBody
   public Result importExcel(MultipartHttpServletRequest request,HttpServletResponse response,ExcelModel excelParam)  throws Exception{
	   return excelService.importExcel(request.getFile("file"), excelParam);
   }
	/**
    * 下发制表_导出下发考核列表数据
    * @param response
    * @param custom
    * @param br
    */
	@RequestMapping("exportExcel")
	public void  exportExcel(HttpServletResponse response,String key){
		//获得标题
		String title=excelService.queryExcelTitle(key);
		//获得数据
		Workbook workbook =excelService.exportExcel(key,title);
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
       }
	}
	 
}
