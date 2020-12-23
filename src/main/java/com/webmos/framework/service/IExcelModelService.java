package com.webmos.framework.service;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import com.webmos.framework.model.DataModel;
import com.webmos.framework.model.Result;

public interface IExcelModelService {

	String queryExcelTitle(String modid);

	Workbook downExcelTemplate(String modid)throws Exception;

	Result importExcelTemplateData(MultipartFile file, String modid) throws Exception;

	Workbook exportExcelTemplateData(DataModel dataModel)throws Exception;

}
