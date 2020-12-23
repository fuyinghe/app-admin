package com.hrbwmxx.framework.fj.service;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import com.hrbwmxx.framework.fj.model.ExcelModel;
import com.hrbwmxx.framework.model.Result;

public interface IExcelService {

	public Result importExcel(MultipartFile file, ExcelModel excelParam) throws Exception;

	public Workbook exportExcel(String key,String title);

	public String queryExcelTitle(String key);
}
