//package com.webmos.framework.controller;
// 
//import java.io.OutputStream;
//import java.text.ParseException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import javax.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.CollectionUtils;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import com.zfmd.framework.model.Result;
//import com.zfmd.framework.util.CsvExportUtil;
//import com.zfmd.power.service.IPowerService;
//import com.zfmd.power.vo.PowerRateVo;
//import com.zfmd.power.vo.PowerVo;
//
//	@Controller
//	@RequestMapping("power")
//	public class PowerController {
//		
//	@Autowired
//	private IPowerService powerService;
//	
//	@RequestMapping("queryPowerState")
//	@ResponseBody
//	public Result queryPowerState() {
//		return powerService.queryPowerState();
//	}
//	
//	@RequestMapping("queryPowerAudio")
//	@ResponseBody
//	public Result queryPowerAudio() {
//		return powerService.queryPowerAudio();
//	}
//	
//	@RequestMapping("queryPowerSuccessRate")
//	@ResponseBody
//	public Result queryPowerSuccessRate(PowerRateVo obj) {
//		return powerService.queryPowerSuccessRate(obj);
//	}
//	
//	@RequestMapping("queryPowerCountDetail")
//	@ResponseBody
//	public Result queryPowerCountDetail(PowerRateVo obj) {
//		return powerService.queryPowerCountDetail(obj);
//	}
//	
//	@RequestMapping("viewPowerReport")
//	@ResponseBody
//	public Result viewPowerReport(PowerRateVo obj) throws ParseException {
//		return powerService.viewPowerReport(obj);
//	}
//	
//	@RequestMapping("exportPowerCountDetail")
//	@ResponseBody
//	public Result exportPowerCountDetail(HttpServletResponse response,PowerRateVo obj) {
//		PowerVo result = new PowerVo();
//		// 查询需要导出的数据
//		if(obj.getTimeUnit()!=null&&obj.getTimeUnit().equals("1")) {
//			obj.setConditionDate(obj.getConditionDate().substring(0,7));
//		}
//        List<PowerRateVo> dataList = powerService.queryPowerDetail(obj);
//        if (CollectionUtils.isEmpty(dataList)) {
//        	result.setErrcode("501");
//        	result.setErrmsg("无可用数据");
//            return result;
//        }
//
//        // 构造导出数据结构
//        String titles = "日期,上报内容,文件名称,状态";  // 设置表头
//        String keys = "dataDate,reportName,fileName,state";  // 设置每列字段
//        
//        // 构造导出数据
//        List<Map<String, Object>> datas = new ArrayList<>();
//        Map<String, Object> map = null;
//        for (PowerRateVo data : dataList) {
//            map = new HashMap<>();
//            map.put("dataDate", data.getDataDate());
//            map.put("reportName", data.getReportName());
//            map.put("fileName", data.getFileName());
//            map.put("state", data.getState());
//            datas.add(map);
//        }
//        // 设置导出文件前缀
//        String fName = obj.getReportType()+"_";
//        // 文件导出
//        try {
//            OutputStream os = response.getOutputStream();
//            CsvExportUtil.responseSetProperties(fName, response);
//            CsvExportUtil.doExport(datas, titles, keys, os);
//            os.close();
//        } catch (Exception e) {
//            //logger.error("导出失败", e.getMessage());
//        	result.setErrcode("501");
//        	result.setErrmsg("导出失败");
//            return result;
//        }
//        return result;
//	}
//	
//	
//
//}
